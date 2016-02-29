package org.mule.modules.amazonroute53;

import org.mule.api.annotations.Config;
import org.mule.api.annotations.Connector;
import org.mule.api.annotations.Processor;
import org.mule.api.annotations.param.RefOnly;
import org.mule.modules.amazonroute53.config.ConnectorConfig;

import com.amazonaws.services.route53.model.GetGeoLocationRequest;
import com.amazonaws.services.route53.model.GetGeoLocationResult;
import com.amazonaws.services.route53.model.ListHostedZonesRequest;
import com.amazonaws.services.route53.model.ListHostedZonesResult;
import com.amazonaws.services.route53.model.ListResourceRecordSetsRequest;
import com.amazonaws.services.route53.model.ListResourceRecordSetsResult;

@Connector(name="amazon-route53", friendlyName="Amazon Route 53")
public class AmazonRoute53Connector {

    @Config
    ConnectorConfig config;

    @Processor
    public ListHostedZonesResult listHostedZones(@RefOnly ListHostedZonesRequest request) {
        return this.config.getClient().listHostedZones(request);
    }

    @Processor
    public GetGeoLocationResult getGeoLocations(@RefOnly GetGeoLocationRequest request) {
        return this.config.getClient().getGeoLocation(request);
    }
    
    @Processor
    public ListResourceRecordSetsResult getResourceRecordSets(@RefOnly ListResourceRecordSetsRequest request) {
        return this.config.getClient().listResourceRecordSets(request);
    }

    public ConnectorConfig getConfig() {
        return config;
    }

    public void setConfig(ConnectorConfig config) {
        this.config = config;
    }

}