package com.adobe.aem.guides.wknd.core.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.iterators.TransformIterator;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.spi.LoggerFactory;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceMetadata;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceUtil;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.wrappers.ValueMapDecorator;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.spi.injectorspecific.InjectAnnotation;

import com.adobe.granite.ui.components.ds.DataSource;
import com.adobe.granite.ui.components.ds.EmptyDataSource;
import com.adobe.granite.ui.components.ds.SimpleDataSource;
import com.adobe.granite.ui.components.ds.ValueMapResource;
import com.day.cq.commons.jcr.JcrConstants;
import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import org.slf4j.Logger;

@Model(adaptables = { SlingHttpServletRequest.class,
		Resource.class }, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class DatasourceChildPageModel {
	
	//static final Logger log = LoggerFactory.getLogger(DatasourceChildPageModel.class);
			

	@Inject
	private SlingHttpServletRequest slingHttpServletRequest;

	@Self
	Resource resource;

	@Inject
	QueryBuilder queryBuilder;

	@Inject
	ResourceResolver resourceResolver;

	@PostConstruct
	protected void init() throws RepositoryException {

		// final ResourceResolver resolver = getResource().getResourceResolver();

		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("path", "/content/we-retail");
		paramMap.put("type", "cq:Page");

		ValueMap vm = new ValueMapDecorator(new HashMap<String, Object>());

		Query query = queryBuilder.createQuery(PredicateGroup.create(paramMap),
				resourceResolver.adaptTo(Session.class));
		SearchResult result = query.getResult();
		final Map<String, String> childPages = new LinkedHashMap<String, String>();
		for (Hit hit : result.getHits()) {
			String title = hit.getTitle();
			String path = hit.getPath();
			childPages.put(path, title);
		}

		@SuppressWarnings("unchecked")
		DataSource ds = new SimpleDataSource(new TransformIterator(childPages.keySet().iterator(), new Transformer() {

			@Override

			public Object transform(Object o) {
				String childPagesTitle = (String) o;

				ValueMap vm = new ValueMapDecorator(new HashMap<String, Object>());

				vm.put("value", childPagesTitle);
				vm.put("text", childPages.get(childPagesTitle));

				return new ValueMapResource(resourceResolver, new ResourceMetadata(), "nt:unstructured", vm);
			}
		}));

		slingHttpServletRequest.setAttribute(DataSource.class.getName(), ds);

	}

}
