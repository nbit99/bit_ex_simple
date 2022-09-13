package io.ex.notice.utils.encrypt.ecdsa;

import io.ex.notice.APIConstants;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/***
 * ECDSA签名算法使用 SHA256withRSA
 */
public class ECDSABase64Utils {

    private static final String ECDSA_SIGN_ALGORITHM = "SHA1withECDSA";
    private static final String ECDSA_KEY_ALGORITHM = "EC";

    private static final String KEY_RANDOM_ALGORITHM = "SHA1PRNG";

    /***
     * 获取签名
     * @param privateKey
     * @param message
     * @return
     * @throws Exception
     */
    private static String getSign(PrivateKey privateKey, String message) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, UnsupportedEncodingException {
        //执行签名
        Signature signature = Signature.getInstance(ECDSA_SIGN_ALGORITHM);
        signature.initSign(privateKey);
        signature.update(message.getBytes(CharsetEnum.UTF_8.charset()));
        byte[] sign = signature.sign();

        return Base64.getEncoder().encodeToString(sign);
//        return Hex.encodeHexString(sign);å
    }


    /***
     * 从string转private key
     * @param key
     * @return
     * @throws Exception
     */
    public static PrivateKey getPrivateKey(String key) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] bytes = Base64.getDecoder().decode(key);//DatatypeConverter.parseHexBinary(key);

        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(bytes);
        KeyFactory keyFactory = KeyFactory.getInstance(ECDSA_KEY_ALGORITHM);
        return keyFactory.generatePrivate(keySpec);
    }

    public static String sign(String message, String secretKey)
            throws InvalidKeySpecException, NoSuchAlgorithmException, SignatureException, InvalidKeyException, UnsupportedEncodingException {
        if (StringUtils.isEmpty(secretKey) || StringUtils.isEmpty(message)) {
            return APIConstants.EMPTY;
        }

        PrivateKey privateKey = getPrivateKey(secretKey);

        return getSign(privateKey, message);
        //return Base64.getEncoder().encodeToString(mac.doFinal(preHash.getBytes(CharsetEnum.UTF_8.charset())));
    }

    public static String sign(String timestamp, String method, String requestPath,
                              String queryString, String body, String secretKey)
            throws InvalidKeySpecException, NoSuchAlgorithmException, SignatureException, InvalidKeyException, UnsupportedEncodingException {
        if (StringUtils.isEmpty(secretKey) || StringUtils.isEmpty(method)) {
            return APIConstants.EMPTY;
        }

        PrivateKey privateKey = getPrivateKey(secretKey);
        String preHash = preHash(timestamp, method, requestPath, queryString, body);

        return getSign(privateKey, preHash);
        //return Base64.getEncoder().encodeToString(mac.doFinal(preHash.getBytes(CharsetEnum.UTF_8.charset())));
    }

    public static String preHash(String timestamp, String method, String requestPath,
                                 String queryString, String body) {
        StringBuilder preHash = new StringBuilder();
        preHash.append(timestamp);
        preHash.append(method.toUpperCase());
        preHash.append(requestPath);
        //get方法
        if (StringUtils.isNotEmpty(queryString)) {
            //在queryString前面拼接上？
            preHash.append(APIConstants.QUESTION).append(queryString);
        }
        //post方法
        if (StringUtils.isNotEmpty(body)) {
            preHash.append(body);
        }
        return preHash.toString();
    }


    //验签
    private static boolean verifyECDSA(PublicKey publicKey, String signed, String message) {
        try {
            //验证签名
            Signature signature = Signature.getInstance(ECDSA_SIGN_ALGORITHM);
            signature.initVerify(publicKey);
            signature.update(message.getBytes());
            //byte[] bytes = Hex.decodeHex(signed);
            //
            byte[] bytes = Base64.getDecoder().decode(signed);
            boolean bool = signature.verify(bytes);
            return bool;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean verify(String publicKey, String signed, String message) throws Exception{

        return verifyECDSA(getPublicKey(publicKey), signed, message);

    }


    /**
     * 从string转public key
     */
    public static PublicKey getPublicKey(String key) throws Exception {
        //byte[] bytes = DatatypeConverter.parseHexBinary(key);
        byte[] bytes = Base64.getDecoder().decode(key);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(bytes);
        KeyFactory keyFactory = KeyFactory.getInstance(ECDSA_KEY_ALGORITHM);
        return keyFactory.generatePublic(keySpec);
    }


    /**
     * 生成 share key
     *
     * @param publicStr  公钥字符串
     * @param privateStr 私钥字符串
     * @return
     */
//    public static String genSharedKey(String publicStr, String privateStr) {
//        try {
//            return genSharedKey(getPublicKey(publicStr), getPrivateKey(privateStr));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    /**
     * 生成 share key
     *
     * @param publicKey  公钥
     * @param privateKey 私钥
     * @return
     */
//    public static String genSharedKey(PublicKey publicKey, PrivateKey privateKey) {
//        String sharedKey = "";
//        try {
//            KeyAgreement ka = KeyAgreement.getInstance("ECDH");
//            ka.init(privateKey);
//            ka.doPhase(publicKey, true);
//            sharedKey = Hex.encodeHexString(ka.generateSecret());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return sharedKey;
//    }

    //生成KeyPair
//    private static KeyPair getKeyPair() throws Exception {
//        KeyPairGenerator keyGen = KeyPairGenerator.getInstance(ECDSA_KEY_ALGORITHM);
//        SecureRandom random = SecureRandom.getInstance(KEY_RANDOM_ALGORITHM);
//        keyGen.initialize(256, random);
//        return keyGen.generateKeyPair();
//    }

//    public static EcdsaKeyPair getEcKeyPair() throws Exception {
//        KeyPair keyPair = getKeyPair();
//
//        return new EcdsaKeyPair(Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded()),
//                Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded()));
//    }

}
