package com.rubenvp.quote.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rubenvp.quote.dto.QuoteDto;
import com.rubenvp.quote.model.Quote;
import com.rubenvp.quote.service.QuoteService;

@WebMvcTest(QuoteController.class)
class QuoteControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private QuoteService quoteService;

    @Autowired
    private ObjectMapper objectMapper;

    private Page<Quote> mockedQuotePage;
    private PageRequest pageRequest;
    private List<Quote> mockedQuotes;

    final private String CONTENT_TYPE = "application/json;charset=UTF-8";

    @BeforeEach
    void setUp() throws Exception {

        // Mock quotes
        pageRequest = PageRequest.of(0, 3);
        mockedQuotes = new ArrayList<Quote>();
        mockedQuotes.add(new Quote(1L, "Quote 1", "Author 1", "Category 1"));
        mockedQuotes.add(new Quote(2L, "Quote 2", "Author 1", "Category 1"));
        mockedQuotes.add(new Quote(3L, "Quote 3", "Author 1", "Category 1"));
        mockedQuotePage = new PageImpl<>(mockedQuotes, pageRequest, mockedQuotes.size());

    }

    @Test
    void getPageableQuotes() throws Exception {

        Mockito.doReturn(mockedQuotePage).when(quoteService).getPageableQuotes(0, 3);

        mvc.perform(MockMvcRequestBuilders.get("/quotes?page=0&size=3").accept(CONTENT_TYPE))
                .andExpect(status().isOk())
                .andDo(print());

        verify(quoteService, times(1)).getPageableQuotes(0, 3);
    }

    @Test
    void getPageableQuotesNotFound() throws Exception {

        mockedQuotePage = new PageImpl<>(new ArrayList<Quote>(), pageRequest, 0);
        Mockito.doReturn(mockedQuotePage).when(quoteService).getPageableQuotes(0, 3);

        mvc.perform(MockMvcRequestBuilders.get("/quotes?page=0&size=3").accept(CONTENT_TYPE))
                .andExpect(status().isOk())
                .andDo(print());

        verify(quoteService, times(1)).getPageableQuotes(0, 3);

    }

    @Test
    void getPageableQuotesWithoutPageAndSizeNumber() throws Exception {

        Mockito.doReturn(mockedQuotePage).when(quoteService).getPageableQuotes(0, 10);

        mvc.perform(MockMvcRequestBuilders.get("/quotes").accept(CONTENT_TYPE))
                .andExpect(status().isOk())
                .andDo(print());

        verify(quoteService, times(1)).getPageableQuotes(0, 10);
    }

    @Test
    void getPageableQuotesByCategory() throws Exception {

        // Mock quotes from category
        Mockito.doReturn(mockedQuotePage).when(quoteService).getPageableQuotesByCategory(0, 3, "Category 1");

        mvc.perform(MockMvcRequestBuilders.get("/quotes?page=0&size=3&category=Category 1")
                .accept(CONTENT_TYPE)).andExpect(status().isOk()).andDo(print());

        verify(quoteService, times(1)).getPageableQuotesByCategory(0, 3, "Category 1");
    }

    @Test
    void getPageableQuotesByCategoryNotFound() throws Exception {

        // Mock quotes from category
        mockedQuotePage = new PageImpl<>(new ArrayList<Quote>(), pageRequest, 0);
        Mockito.doReturn(mockedQuotePage).when(quoteService).getPageableQuotesByCategory(0, 3, "Category 1");

        mvc.perform(MockMvcRequestBuilders.get("/quotes?page=0&size=3&category=Category 1")
                .accept(CONTENT_TYPE)).andExpect(status().isOk()).andDo(print());

        verify(quoteService, times(1)).getPageableQuotesByCategory(0, 3, "Category 1");

    }

    @Test
    void getPageableQuotesByAuthor() throws Exception {

        // Mock quotes from author
        Mockito.doReturn(mockedQuotePage).when(quoteService).getPageableQuotesByAuthor(0, 3, "Author 1");

        mvc.perform(MockMvcRequestBuilders.get("/quotes?page=0&size=3&author=Author 1")
                .accept(CONTENT_TYPE)).andExpect(status().isOk()).andDo(print());

        verify(quoteService, times(1)).getPageableQuotesByAuthor(0, 3, "Author 1");

    }

    @Test
    void getPageableQuotesByAuthorNotFound() throws Exception {

        // Mock quotes from author
        mockedQuotePage = new PageImpl<>(new ArrayList<Quote>(), pageRequest, 0);
        Mockito.doReturn(mockedQuotePage).when(quoteService).getPageableQuotesByAuthor(0, 3, "Author 1");

        mvc.perform(MockMvcRequestBuilders.get("/quotes?page=0&size=3&author=Author 1")
                .accept(CONTENT_TYPE)).andExpect(status().isOk()).andDo(print());

        verify(quoteService, times(1)).getPageableQuotesByAuthor(0, 3, "Author 1");

    }

    @Test
    void getPageableQuotesByTextSearch() throws Exception {

        // Mock quotes from search term
        Mockito.doReturn(mockedQuotePage).when(quoteService).getPageableQuotesByTextSearch(0, 3, "Quote");

        mvc.perform(MockMvcRequestBuilders.get("/quotes?page=0&size=3&search=Quote")
                .accept(CONTENT_TYPE)).andExpect(status().isOk()).andDo(print());

        verify(quoteService, times(1)).getPageableQuotesByTextSearch(0, 3, "Quote");
    }

    @Test
    void getPageableQuotesByTextSearchNotFound() throws Exception {

        // Mock quotes from search term
        mockedQuotePage = new PageImpl<>(new ArrayList<Quote>(), pageRequest, 0);
        Mockito.doReturn(mockedQuotePage).when(quoteService).getPageableQuotesByTextSearch(0, 3, "Quote");

        mvc.perform(MockMvcRequestBuilders.get("/quotes?page=0&size=3&search=Quote")
                .accept(CONTENT_TYPE)).andExpect(status().isOk()).andDo(print());

        verify(quoteService, times(1)).getPageableQuotesByTextSearch(0, 3, "Quote");
    }

    @Test
    void getQuoteById() throws Exception {

        // Mock quote by id
        Mockito.doReturn(mockedQuotes.get(0)).when(quoteService).getQuoteById(1L);

        mvc.perform(MockMvcRequestBuilders.get("/quotes/1").accept(CONTENT_TYPE))
                .andExpect(status().isOk()).andDo(print())
                .andExpect(result -> objectMapper.writeValueAsString(mockedQuotes.get(0)));

        verify(quoteService, times(1)).getQuoteById(1L);
    }

    @Test
    void getQuoteByIdNotFound() throws Exception {

        // Mock quote by id
        Mockito.doReturn(null).when(quoteService).getQuoteById(1L);

        // Test if Exception is thrown
        mvc.perform(MockMvcRequestBuilders.get("/quotes/1").accept(CONTENT_TYPE))
                .andExpect(status().isNotFound())
                .andDo(print());

        verify(quoteService, times(1)).getQuoteById(1L);
    }

    @Test
    void getRandomQuote() throws Exception {

        // Mock random quote
        Mockito.doReturn(mockedQuotes.get(0)).when(quoteService).getRandomQuote();

        mvc.perform(MockMvcRequestBuilders.get("/quotes/random").accept(CONTENT_TYPE))
                .andExpect(status().isOk()).andDo(print())
                .andExpect(result -> objectMapper.writeValueAsString(mockedQuotes.get(0)));

        verify(quoteService, times(1)).getRandomQuote();
    }

    @Test
    void addNewQuoteToTheDatabase() throws Exception {

        // Mock post request with quoteDto, author and category str
        QuoteDto quoteToAdd = new QuoteDto("Quote 1", "Author 1", "Category 1");
        Quote quote = new Quote(1L, "Quote 1", "Author 1", "Category 1");

        // Mock quote added
        Mockito.doReturn(quote).when(quoteService).addQuote(quoteToAdd.toQuote());

        // Content for post request
        String content = objectMapper.writeValueAsString(quoteToAdd);

        mvc.perform(MockMvcRequestBuilders.post("/quotes").contentType(CONTENT_TYPE)
                .content(content != null ? content : "")
                .accept(CONTENT_TYPE))
                .andExpect(status().isOk())
                .andExpect(result -> objectMapper.writeValueAsString(quote));

    }

    @Test
    void updateQuoteFromTheDatabase() throws Exception {

        // Mock put request with quoteDto
        QuoteDto quoteToUpdate = new QuoteDto("Quote 1", "Author 1", "Category 1");
        Quote quote = new Quote(1L, "Quote 1", "Author 1", "Category 1");

        // Mock quote updated
        Mockito.doReturn(quote).when(quoteService).getQuoteById(1L);
        Mockito.doReturn(quote).when(quoteService).updateQuote(any(Quote.class), any(QuoteDto.class));

        // Content for put request
        String content = objectMapper.writeValueAsString(quoteToUpdate);

        mvc.perform(MockMvcRequestBuilders.put("/quotes/{id}", 1L).contentType(CONTENT_TYPE)
                .content(content != null ? content : "")
                .accept(CONTENT_TYPE))
                .andExpect(status().isOk())
                .andExpect(result -> objectMapper.writeValueAsString(quote));

    }

    @Test
    void deleteQuoteFromTheDatabase() throws Exception {

        // Mock delete request with PathVariable id
        Quote quote = new Quote(1L, "Quote 1", "Author 1", "Category 1");

        // Mock quote deleted
        Mockito.doReturn(quote).when(quoteService).getQuoteById(1L);
        Mockito.doReturn(quote).when(quoteService).deleteQuote(quote);

        mvc.perform(MockMvcRequestBuilders.delete("/quotes/{id}", 1L)
                .contentType(CONTENT_TYPE)
                .accept(CONTENT_TYPE))
                .andExpect(status().isOk())
                .andExpect(result -> objectMapper.writeValueAsString(quote));

    }
}
