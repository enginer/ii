package org.ayfaar.app.controllers;

import org.ayfaar.app.dao.CommonDao;
import org.ayfaar.app.dao.ItemDao;
import org.ayfaar.app.dao.TermDao;
import org.ayfaar.app.model.Term;
import org.ayfaar.app.spring.Model;
import org.ayfaar.app.utils.AliasesMap;
import org.ayfaar.app.utils.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.ayfaar.app.utils.RegExpUtils.w;
import static org.ayfaar.app.utils.RegExpUtils.W;
import static org.ayfaar.app.utils.TermUtils.isCosmicCode;

@Controller
@RequestMapping("search")
public class SearchController {
    @Autowired AliasesMap aliasesMap;
    @Autowired TermDao termDao;
    @Autowired ItemDao itemDao;
    @Autowired CommonDao commonDao;

    /*public Object search(String query) {
        ModelMap modelMap = new ModelMap();
        modelMap.put("terms", searchAsTerm(query));
        modelMap.put("items", searchInItems(query));
        return modelMap;
    }

    private List searchInItems(String query) {
        return null;
    }*/

    @RequestMapping("content")
    @Model
    @ResponseBody
    private List<ModelMap> searchInContent(@RequestParam String query) {
        List<ModelMap> modelMaps = new ArrayList<ModelMap>();
        List<Content> items = commonDao.findInAllContent(query);
        query = query.replaceAll("\\*", "["+ w +"]*");
		Pattern pattern = Pattern.compile("([^\\.\\?!]*)(["+ W +"|^])(" + query + ")(["+ W +"|$])([^\\.\\?!]*)",
                Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
        System.out.println("тест" + query + "\n");
        for (Content item : items) {
            ModelMap map = new ModelMap();
            map.put("uri", item.getUri());
            map.put("name", item.getName());
            Matcher matcher = pattern.matcher(item.getContent());
            if (matcher.find()) {
                String quote = matcher.group(1)+matcher.group(2)+
                        "<strong>"+matcher.group(3)+"</strong>"+
                        matcher.group(4)+matcher.group(5);
                map.put("quote", quote.trim());
                modelMaps.add(map);
            }
        }
        return modelMaps;
    }

    @RequestMapping("term")
    @Model
    @ResponseBody
    private List<Term> searchAsTerm(@RequestParam String query) {
        List<Term> allTerms = aliasesMap.getAllTerms();
        List<String> matches = new ArrayList<String>();

        Pattern pattern = null;
        if (isCosmicCode(query)) {
            String regexp = "";
            for (int i=0; i< query.length(); i++) {
                if (i > 0 && query.charAt(i) == query.charAt(i-1)) {
                    continue;
                }
                regexp += "("+query.charAt(i)+")+";
            }
            pattern = Pattern.compile(regexp.toLowerCase());
        }

        for (Term term : allTerms) {
            if (term.getName().toLowerCase().equals(query.toLowerCase())) {
                matches.add(0, term.getName());
            } else if (term.getName().toLowerCase().contains(query.toLowerCase())
                    || pattern != null && pattern.matcher(term.getName().toLowerCase()).find()) {
                matches.add(term.getName());
            }
        }

        List<Term> terms = new ArrayList<Term>();
        for (String match : matches) {
            Term prime = aliasesMap.get(match).getTerm();
            boolean has = false;
            for (Term term : terms) {
                if (term.getUri().equals(prime.getUri())) {
                    has = true;
                }
            }

            if (!has) terms.add(prime);
        }

        return terms;
    }
}
