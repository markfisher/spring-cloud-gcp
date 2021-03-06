/*
 *  Copyright 2017 original author or authors.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.springframework.integration.gcp.outbound;

import org.springframework.cloud.gcp.pubsub.core.PubsubTemplate;
import org.springframework.integration.handler.AbstractMessageHandler;
import org.springframework.messaging.Message;
import org.springframework.util.Assert;

/**
 * Sends messages to Google Cloud Pub/Sub by delegating to {@link PubsubTemplate}.
 *
 * @author João André Martins
 */
public class PubsubMessageHandler extends AbstractMessageHandler {

	private final PubsubTemplate pubsubTemplate;

	private String topic;

	public PubsubMessageHandler(PubsubTemplate pubsubTemplate) {
		this.pubsubTemplate = pubsubTemplate;
	}

	@Override
	protected void handleMessageInternal(Message<?> message) throws Exception {
		this.pubsubTemplate.send(this.topic, message);
	}

	public String getTopic() {
		return this.topic;
	}

	public void setTopic(String topic) {
		Assert.notNull(topic, "The topic can't be null.");
		this.topic = topic;
	}
}
