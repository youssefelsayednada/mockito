package portfoliomocking;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PortfolioTester {
    
    @InjectMocks
    Portfolio portfolio = new Portfolio();

    @Mock
    StockService stockServiceMock;

    @Test
    public void testGetMarketValue() {
        List<Stock> stocks = new ArrayList<>();
        Stock googleStock = new Stock("1","Google", 10);
        Stock microsoftStock = new Stock("2","Microsoft",100);

        stocks.add(googleStock);
        stocks.add(microsoftStock);		

        // mock the behavior of stock service to return the value of various stocks
        when(stockServiceMock.getPrice(googleStock)).thenReturn(50.00);
        when(stockServiceMock.getPrice(microsoftStock)).thenReturn(1000.00);

        //add stocks to the portfolio
        portfolio.setStocks(stocks);

        //set the stockService to the portfolio
        portfolio.setStockService(stockServiceMock);

        double marketValue = portfolio.getMarketValue();
        Assert.assertEquals(100500.0, marketValue, 1e-15);
        verify(stockServiceMock, times(1)).getPrice(googleStock);

    }

}
