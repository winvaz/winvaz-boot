package com.icore.winvaz.restapi.util;

import io.github.swagger2markup.GroupBy;
import io.github.swagger2markup.Language;
import io.github.swagger2markup.Swagger2MarkupConfig;
import io.github.swagger2markup.Swagger2MarkupConverter;
import io.github.swagger2markup.builder.Swagger2MarkupConfigBuilder;
import io.github.swagger2markup.markup.builder.MarkupLanguage;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;

/**
 * @Deciption 测试swagger转Markdown
 * @Author wdq
 * @Create 2021/3/19 10:00
 * @Version 1.0.0
 */
public class ToDoc {

    // 智慧监管系统
    // private static String swaggerUrl = "http://139.227.231.137/api/supervision/sz/v2/api-docs";

    // 智慧企业系统
    // private static String swaggerUrl = "http://139.227.231.137/api/corp/v2/api-docs";

    // 智慧港口系统
    private static String swaggerUrl = "http://139.227.231.137/api/port/scct/v2/api-docs";

    @Test
    public void generateAsciiDocs() throws MalformedURLException {
        // 输出Ascii格式
        Swagger2MarkupConfig config = new Swagger2MarkupConfigBuilder()
                .withMarkupLanguage(MarkupLanguage.ASCIIDOC)
                .withOutputLanguage(Language.ZH)
                .withPathsGroupedBy(GroupBy.TAGS)
                .withGeneratedExamples()
                .withoutInlineSchema()
                .build();

        Swagger2MarkupConverter.from(new URL(swaggerUrl))
                .withConfig(config)
                .build()
                .toFolder(Paths.get("./docs/asciidoc/generated"));
    }

    /**
     * 生成Markdown格式文件
     * @author wdq
     * @create 2021/3/19 10:12
     * @param
     * @Return void
     * @exception
     */
    @Test
    public void generateMarkdowunDocs() throws MalformedURLException {
        // 输出Markdown格式
        Swagger2MarkupConfig config = new Swagger2MarkupConfigBuilder()
                .withMarkupLanguage(MarkupLanguage.MARKDOWN)
                .withOutputLanguage(Language.ZH)
                .withPathsGroupedBy(GroupBy.TAGS)
                .withGeneratedExamples()
                .withoutInlineSchema()
                .build();

        Swagger2MarkupConverter.from(new URL(swaggerUrl))
                .withConfig(config)
                .build()
                .toFolder(Paths.get("./docs/markdown/generated"));
    }

    /**
     * 生成Confluence格式文件
     * @author wdq
     * @create 2021/3/19 10:13
     * @param
     * @Return void
     * @exception
     */
    @Test
    public void generateConfluenceDocs() throws MalformedURLException {
        // 输出Markdown格式
        Swagger2MarkupConfig config = new Swagger2MarkupConfigBuilder()
                .withMarkupLanguage(MarkupLanguage.CONFLUENCE_MARKUP)
                .withOutputLanguage(Language.ZH)
                .withPathsGroupedBy(GroupBy.TAGS)
                .withGeneratedExamples()
                .withoutInlineSchema()
                .build();

        Swagger2MarkupConverter.from(new URL(swaggerUrl))
                .withConfig(config)
                .build()
                .toFolder(Paths.get("./docs/confluence/generated"));
    }

    /**
     * 生成AsciiDocs格式文件，并汇总成一个文件
     * @author wdq
     * @create 2021/3/19 10:13
     * @param
     * @Return void
     * @exception
     */
    @Test
    public void generateAsciiDocsToFile() throws MalformedURLException {
        // 输出Markdown格式
        Swagger2MarkupConfig config = new Swagger2MarkupConfigBuilder()
                .withMarkupLanguage(MarkupLanguage.ASCIIDOC)
                .withOutputLanguage(Language.ZH)
                .withPathsGroupedBy(GroupBy.TAGS)
                .withGeneratedExamples()
                .withoutInlineSchema()
                .build();

        Swagger2MarkupConverter.from(new URL(swaggerUrl))
                .withConfig(config)
                .build()
                .toFile(Paths.get("./docs/asciidocs/generated/all"));
    }

    /**
     * 生成Markdown格式文件，并汇总成一个文件
     * @author wdq
     * @create 2021/3/19 10:13
     * @param
     * @Return void
     * @exception
     */
    @Test
    public void generateMarkdownDocsToFile() throws MalformedURLException {
        // 输出Markdown格式
        Swagger2MarkupConfig config = new Swagger2MarkupConfigBuilder()
                .withMarkupLanguage(MarkupLanguage.MARKDOWN)
                .withOutputLanguage(Language.ZH)
                .withPathsGroupedBy(GroupBy.TAGS)
                .withGeneratedExamples()
                .withoutInlineSchema()
                .build();

        Swagger2MarkupConverter.from(new URL(swaggerUrl))
                .withConfig(config)
                .build()
                .toFile(Paths.get("./docs/markdown/generated/all"));
    }



}
