
package io.ex.notice.utils.http;

import javax.net.ssl.TrustManagerFactory;

/**
 * <b>功能说明:
 * </b>
 * @author  ted
 */
public class TrustKeyStore {
	private TrustManagerFactory trustManagerFactory;
	
	TrustKeyStore(TrustManagerFactory trustManagerFactory){
		this.trustManagerFactory = trustManagerFactory;
	}
	
	TrustManagerFactory getTrustManagerFactory(){
		return trustManagerFactory;
	}
}
