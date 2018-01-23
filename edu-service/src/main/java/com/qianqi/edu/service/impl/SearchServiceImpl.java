package com.qianqi.edu.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qianqi.edu.mapper.QuestionJudgeMapper;
import com.qianqi.edu.pojo.QuestionJudge;
import com.qianqi.edu.pojo.QuestionJudgeExample;
import com.qianqi.edu.pojo.common.SearchItem;
import com.qianqi.edu.service.SearchService;

@Service
public class SearchServiceImpl implements SearchService{
	@Autowired
	private QuestionJudgeMapper questionJudgeMapper;
	@Autowired
	private SolrServer solrServer;
	
	@Override
	public void synchroSearchItem() {
		
		QuestionJudgeExample example = new QuestionJudgeExample();
		List<QuestionJudge> list = questionJudgeMapper.selectByExampleWithBLOBs(example);
		try {
			for(QuestionJudge question : list)
			{
				//创建文档对象
				SolrInputDocument document = new SolrInputDocument();
				//向文档对象中添加域
				document.addField("id", question.getId());
				document.addField("question_context", question.getContext());
				document.addField("question_knowledge_point", question.getKnowledgePoint());
				//把文档对象写入索引库
				solrServer.add(document);
			}
			//提交
			solrServer.commit();
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addSearchItem(SearchItem item) {
		try {
			//创建文档对象
			SolrInputDocument document = new SolrInputDocument();
			//向文档对象中添加域
			document.addField("id", item.getId());
			document.addField("question_context", item.getContext());
			document.addField("question_knowledge_point", item.getKnowledgePoint());
			//把文档对象写入索引库
			solrServer.add(document);
			//提交
			solrServer.commit();
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteSearchItem(String id) {
		try {
			solrServer.deleteById(id);
			solrServer.commit();
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<SearchItem> searchQuestion(String keyword, int page, int rows) {
		//创建一个SolrQuery对象
		SolrQuery query = new SolrQuery();
		//设置查询条件
		query.setQuery(keyword);
		//设置分页条件
		if (page <=0 ) page =1;
		query.setStart((page - 1) * rows);
		query.setRows(rows);
		//设置默认搜索域
		query.set("df", "question_context");
		//开启高亮显示
		query.setHighlight(true);
		query.addHighlightField("question_context");
		query.setHighlightSimplePre("<em style=\"color:red\">");
		query.setHighlightSimplePost("</em>");
		
		List<SearchItem> items = new ArrayList<>();
		//根据query查询索引库
		try {
			QueryResponse queryResponse = solrServer.query(query);
			//取查询结果。
			SolrDocumentList solrDocumentList = queryResponse.getResults();
			//取查询结果总记录数
//			long numFound = solrDocumentList.getNumFound();
			//取列表，需要取高亮显示
			Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
			for (SolrDocument solrDocument : solrDocumentList)
			{
				SearchItem item = new SearchItem();
				item.setId((String) solrDocument.get("id"));
				item.setKnowledgePoint((String) solrDocument.get("question_knowledge_point"));
				//取高亮显示
				List<String> list = highlighting.get(solrDocument.get("id")).get("question_context");
				String context = "";
				if (list != null && list.size() > 0) {
					context = list.get(0);
				} else {
					context = (String) solrDocument.get("question_context");
				}
				item.setContext(context);
				//添加到商品列表
				items.add(item);
			}
		} catch (SolrServerException e) {
			e.printStackTrace();
		}
				
		return items;
	}

}
