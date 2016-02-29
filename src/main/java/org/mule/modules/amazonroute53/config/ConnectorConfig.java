package org.mule.modules.amazonroute53.config;

import org.mule.api.ConnectionException;
import org.mule.api.annotations.Configurable;
import org.mule.api.annotations.Connect;
import org.mule.api.annotations.ConnectionIdentifier;
import org.mule.api.annotations.Disconnect;
import org.mule.api.annotations.TestConnectivity;
import org.mule.api.annotations.ValidateConnection;
import org.mule.api.annotations.components.ConnectionManagement;
import org.mule.api.annotations.display.Password;
import org.mule.api.annotations.param.ConnectionKey;
import org.mule.api.annotations.param.RefOnly;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.route53.AmazonRoute53Client;

@ConnectionManagement(friendlyName = "Configuration")
public class ConnectorConfig {
	@Configurable
    private Regions region;
    private AmazonRoute53Client client;


    /**
     * Connect
     *
     * @param username A username
     * @param password A password
     * @throws ConnectionException
     */
    @Connect
    @TestConnectivity
    public void connect(@ConnectionKey String awsKey, @Password String awsSecret)
        throws ConnectionException {
    	this.client = new AmazonRoute53Client(new BasicAWSCredentials(awsKey, awsSecret));
    	this.client.setRegion(Region.getRegion(this.region));
    	try {
        	this.client.listHostedZones();
    	} catch (Exception e) {
        	throw new ConnectionException(org.mule.api.ConnectionExceptionCode.INCORRECT_CREDENTIALS, e.getMessage(), e.getMessage(), e);
    	}
    }


    /**
     * Disconnect
     */
    @Disconnect
    public void disconnect() {
    	this.client = null;
    }

    /**
     * Are we connected
     */
    @ValidateConnection
    public boolean isConnected() {
        return (this.client != null);
    }

    /**
     * Are we connected
     */
    @ConnectionIdentifier
    public String connectionId() {
        return "amazon-route-53-001";
    }

	public AmazonRoute53Client getClient() {
		return client;
	}


	public Regions getRegion() {
		return region;
	}


	public void setRegion(Regions region) {
		this.region = region;
	}

}