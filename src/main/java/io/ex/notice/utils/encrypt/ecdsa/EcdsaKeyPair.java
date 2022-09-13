package io.ex.notice.utils.encrypt.ecdsa;

import java.io.Serializable;

public class EcdsaKeyPair implements Serializable {

    private String pubKey;
    private String priKey;

    public EcdsaKeyPair(String pubKey, String priKey) {
        this.pubKey = pubKey;
        this.priKey = priKey;
    }


    public String getPubKey() {
        return pubKey;
    }

    public String getPriKey() {
        return priKey;
    }

}
