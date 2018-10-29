/*
 * Copyright Elasticsearch B.V. and/or licensed to Elasticsearch B.V. under one
 * or more contributor license agreements. Licensed under the Elastic License;
 * you may not use this file except in compliance with the Elastic License.
 */

package org.elasticsearch.xpack.ccr.rest;

import org.elasticsearch.client.node.NodeClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.rest.BaseRestHandler;
import org.elasticsearch.rest.RestController;
import org.elasticsearch.rest.RestRequest;
import org.elasticsearch.rest.action.RestToXContentListener;
import org.elasticsearch.xpack.core.ccr.action.StatsAction;

import java.io.IOException;

public class RestAutoFollowStatsAction extends BaseRestHandler {

    public RestAutoFollowStatsAction(final Settings settings, final RestController controller) {
        super(settings);
        controller.registerHandler(RestRequest.Method.GET, "/_ccr/stats", this);
    }

    @Override
    public String getName() {
        return "ccr_auto_follow_stats";
    }

    @Override
    protected RestChannelConsumer prepareRequest(final RestRequest restRequest, final NodeClient client) throws IOException {
        final StatsAction.Request request = new StatsAction.Request();
        return channel -> client.execute(StatsAction.INSTANCE, request, new RestToXContentListener<>(channel));
    }

}
