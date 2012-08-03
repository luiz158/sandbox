package org.cardsample.client.template;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;
import org.cardsample.client.marshall.IMarshaller;
import org.cardsample.common.ClientOperation;
import org.cardsample.common.IEntity;
import org.cardsample.common.IOperations;

import com.jayway.restassured.specification.RequestSpecification;

public interface IRESTTemplate<T extends IEntity> extends IOperations<T>, IEntityOperations<T>, ITemplateAsResponse<T>, ITemplateAsURI<T> {

    String getURI();

    // authentication

    RequestSpecification givenAuthenticated();

    IMarshaller getMarshaller();

    // search

    List<T> search(final Pair<Long, ClientOperation> idOp, final Pair<String, ClientOperation> nameOp);

    List<T> search(final Pair<Long, ClientOperation> idOp, final Pair<String, ClientOperation> nameOp, final int page, final int size);

}
