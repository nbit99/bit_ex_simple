
package io.ex.notice.utils.http;

import javax.net.ssl.KeyManagerFactory;

/**
 * <b>功能说明:</b>
 * @author  Ted
 */
public class ClientKeyStore {
	private KeyManagerFactory keyManagerFactory;
	
	ClientKeyStore(KeyManagerFactory keyManagerFactory){
		this.keyManagerFactory = keyManagerFactory;
	}
	
	KeyManagerFactory getKeyManagerFactory(){
		return keyManagerFactory;
	}
}
