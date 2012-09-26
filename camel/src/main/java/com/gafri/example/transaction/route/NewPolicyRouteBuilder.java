package com.gafri.example.transaction.route;

import com.gafri.example.transaction.domain.entity.Policy;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.jaxb.JaxbDataFormat;

import javax.xml.bind.JAXBContext;


public class NewPolicyRouteBuilder extends RouteBuilder
{
    @Override
    public void configure() throws Exception
    {
        JaxbDataFormat format = new JaxbDataFormat(JAXBContext.newInstance(Policy.class));
        from("jms:queue:ADD_POLICY").id("new-policy")
                .transacted("TX_REQUIRED")
                .unmarshal(format)
                .beanRef("policyRepository", "add")
                .marshal(format)
                .to("jms:queue:POLICY_EVENT");
    }
}
