package org.touchdata.client.template;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;
import org.touchdata.client.marshall.IMarshaller;
import org.touchdata.common.ClientOperation;
import org.touchdata.common.IEntity;
import org.touchdata.common.IOperations;

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
