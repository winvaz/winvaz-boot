package com.icore.winvaz.javaee.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.TotalHits;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Deciption Lucene测试
 * @Author wdq
 * @Create 2020/7/6 19:59
 * @Version 1.0.0
 */
public class LuceneTest {

    @Test
    public void addTest() throws IOException {

        // 1.1.构建索引存放的目录
        Directory directory = FSDirectory.open(Paths.get("./indexDir"));

        // 1.2.构建IndexWriterConfig
        // 使用默认的标准分词器
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig();

        // 1.构建IndexWriter对象
        IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);

        // 自定义domain对象
        Article article = new Article(
                200L,
                "中国好声音",
                "学好Java扑倒班主任",
                "毕姥爷",
                "www.baidu.com/123.html"
        );
        /*
        // 2.1.构建Document对象
        Document doc = new Document();

        // 将article转成Document
        doc.add(new TextField("id", String.valueOf(article.getId()), Field.Store.YES));
        doc.add(new TextField("title", article.getTitle(), Field.Store.YES));
        doc.add(new TextField("content", article.getContent(), Field.Store.YES));
        doc.add(new TextField("author", article.getAuthor(), Field.Store.YES));
        doc.add(new TextField("url", article.getUrl(), Field.Store.YES));
        */
        // 2.添加Document
        indexWriter.addDocument(article.toDocument());

        // 3.提交
        indexWriter.commit();

        // 4.关闭
        indexWriter.close();
    }

    @Test
    public void searchTest() throws IOException {
        Directory directory = FSDirectory.open(Paths.get("./indexDir"));
        ;
        IndexReader indexReader = DirectoryReader.open(directory);
        // 1.构建IndexSearch对象
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);

        // 2.1.依赖查询条件
        Query query = new TermQuery(new Term("content", "学"));
        // 2.查询
        TopDocs topDocs = indexSearcher.search(query, 10);
        TotalHits totalHits = topDocs.totalHits;
        System.out.println("命中了:" + totalHits.value);
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        for (ScoreDoc scoreDoc : scoreDocs) {
            int doc = scoreDoc.doc;
            System.out.println("docId:" + doc);
            float score = scoreDoc.score;
            System.out.println("socre:" + score);
            // 通过doc查询document
            Document document = indexSearcher.doc(doc);
            System.out.println("id:" + document.get("id"));
            System.out.println("title:" + document.get("title"));
            System.out.println("content:" + document.get("content"));
            System.out.println("author:" + document.get("author"));
            System.out.println("url:" + document.get("url"));
        }
    }
    /*

    @Test
    public void addIndexTest() {
        LuceneDao luceneDao = new LuceneDao();
        // 1.获取IndexWriter
        IndexWriter indexWriter = null;
        // 2.获取Document
        // 自定义domain对象
        Article article = new Article(
                400L,
                "中国好声音",
                "学好Lucene扑倒班主任",
                "毕姥爷",
                "www.baidu.com/123.html"
        );
        // 3.获取Document
        Document doc = article.toDocument();
        try {
            indexWriter = LuceneUtil.getIndexWriter();
            luceneDao.addIndex(indexWriter, doc);
            // 4.提交
            indexWriter.commit();
        } catch (IOException e) {
            try {
                indexWriter.rollback();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Test
    public void addIndexsTest() {
        // 1.添加多个Document
        int length = 28;
        Document[] docs = new Document[length];
        for (int i = 1; i <= length; i++) {
            Article article = new Article((long) i,
                    new StringBuilder().append("中国好声音").append(i).toString(),
                    "学好Java扑倒班主任",
                    new StringBuilder().append("毕姥爷").append(i).toString(),
                    new StringBuilder().append("www.baidu.com/123").append(i).append(".html").toString());
            Document document = article.toDocument();
            docs[i - 1] = document;
        }
        LuceneDao luceneDao = new LuceneDao();
        IndexWriter indexWriter = null;
        try {
            indexWriter = LuceneUtil.getIndexWriter();
            luceneDao.addIndex(indexWriter, docs);
            indexWriter.commit();
        } catch (IOException e) {
            try {
                indexWriter.rollback();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }

    @Test
    public void updateIndexTest() {
        LuceneDao luceneDao = new LuceneDao();
        // 1.获取IndexWriter
        IndexWriter indexWriter = null;
        // 2.获取Document
        // 自定义domain对象
        Article article = new Article(
                500L,
                "中国",
                "学好Hadoop扑倒班主任",
                "毕姥爷",
                "www.baidu.com/321.html"
        );
        // 3.获取Document
        Document doc = article.toDocument();
        try {
            indexWriter = LuceneUtil.getIndexWriter();
            luceneDao.updateIndex(indexWriter, doc, "content", "学");
            indexWriter.commit();
            indexWriter.close();
        } catch (IOException e) {
            try {
                indexWriter.rollback();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Test
    public void deleteIndexTest() {
        LuceneDao luceneDao = new LuceneDao();
        // 1.获取IndexWriter
        IndexWriter indexWriter = null;
        try {
            indexWriter = LuceneUtil.getIndexWriter();
            luceneDao.deleteIndex(indexWriter, "content", "学");
            indexWriter.commit();
            indexWriter.close();
        } catch (IOException e) {
            try {
                indexWriter.rollback();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    */

    @Test
    public void queryIndexTest() throws IOException, ParseException {
        LuceneDao luceneDao = new LuceneDao();
        IndexSearcher indexSearcher = LuceneUtil.getIndexSearcher();
        List<Document> list = luceneDao.queryIndex(indexSearcher, 20, 30, "中", "title", "content");
        for (Document document : list) {
            Article article = Article.parseArticle(document);
            System.out.println(article);
        }
    }
}

class Article {
    private Long id;
    private String title;
    private String content;
    private String author;
    private String url;

    // 创建有参构造后，推荐添加无参构造，防止反射出错
    public Article() {
    }

    public Article(Long id, String title, String content, String author, String url) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", author='" + author + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    /**
     * Article封装成Document
     */
    public Document toDocument() {
        Document doc = new Document();
        doc.add(new StringField("id", String.valueOf(this.getId()), Field.Store.YES));
        doc.add(new TextField("title", this.getTitle(), Field.Store.YES));
        doc.add(new TextField("content", this.getContent(), Field.Store.YES));
        doc.add(new TextField("author", this.getAuthor(), Field.Store.YES));
        doc.add(new TextField("url", this.getUrl(), Field.Store.YES));

        return doc;
    }

    /**
     * Document解析成Article
     */
    public static Article parseArticle(Document doc) {
        Long id = Long.parseLong(doc.get("id"));
        String title = doc.get("title");
        String content = doc.get("content");
        String author = doc.get("author");
        String url = doc.get("url");

        Article article = new Article(id, title, content, author, url);

        return article;
    }
}

class LuceneDao {
    /**
     * 增加
     */
    public void addIndex(IndexWriter indexWriter, Document... docs) throws IOException {
        indexWriter.addDocuments(Arrays.asList(docs));
    }

    /**
     * 修改(查找出符合条件的删除，然后再增加一条)
     *
     * @param indexWriter
     * @param doc         后添加的Document
     * @param field       用于查找的属性名
     * @param value       用于查找的属性值
     * @throws
     * @author wdq
     * @create 2020/7/7 20:40
     * @Return void
     */
    public void updateIndex(IndexWriter indexWriter, Document doc, String field, String value) throws IOException {
        indexWriter.updateDocument(new Term(field, value), doc);
    }

    /**
     * @param indexWriter
     * @param field
     * @param value
     * @throws
     * @Description TODO
     * @author wdq
     * @create 2020/7/7 20:56
     * @Return void
     */
    public void deleteIndex(IndexWriter indexWriter, String field, String value) throws IOException {
        indexWriter.deleteDocuments(new Term(field, value));
    }

    /**
     * @Description TODO
     * @author wdq
     * @create 2020/7/8 21:01
     * @param indexSearcher
     * @param start 从第开始多少页开始查找
     * @param size  要从开始查找的页数的条数
     * @param keyword   要查找的关键字
     * @param fields    要从哪查找关键字所对应的内容
     * @Return java.util.List<org.apache.lucene.document.Document>
     * @exception
     */
    public List<Document> queryIndex(IndexSearcher indexSearcher, int start, int size, String keyword,
                                     String... fields) throws IOException, ParseException {
        List<Document> list = new ArrayList<>();
        QueryParser queryParser = new MultiFieldQueryParser(fields, LuceneUtil.getAnalyzer());
        Query query = queryParser.parse(keyword);
        int sum = start + size;
        TopDocs topDocs = indexSearcher.search(query, sum);
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        sum = Math.min(sum, scoreDocs.length);
        for (int i = start; i < sum; i++) {
            ScoreDoc scoreDoc = scoreDocs[i];
            int docId = scoreDoc.doc;
            System.out.println("docId:" + docId);
            Document doc = indexSearcher.doc(docId);
            list.add(doc);
        }
        return list;
    }
}

/**
 * LuceneUtil
 */
class LuceneUtil {

    private static IndexWriterConfig indexWriterConfig;

    private static Directory directory;

    private static IndexReader indexReader;

    private static Analyzer analyzer = new StandardAnalyzer();

    static {
        try {
            // Directory
            directory = FSDirectory.open(Paths.get("./indexDir"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //
        indexWriterConfig = new IndexWriterConfig();
    }

    public static IndexWriter getIndexWriter() throws IOException {
        return new IndexWriter(directory, indexWriterConfig);
    }

    public static IndexSearcher getIndexSearcher() throws IOException {
        indexReader = DirectoryReader.open(directory);
        return new IndexSearcher(indexReader);
    }

    public static Analyzer getAnalyzer() {
        return analyzer;
    }
}