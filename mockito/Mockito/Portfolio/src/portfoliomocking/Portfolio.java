package portfoliomocking;

import java.util.List;

public class Portfolio {
    
    private List<Stock> stocks;
    private StockService stockService;
    
    public void setStocks(List<Stock> stocks) {
        this.stocks = stocks;
    }
    
    public void setStockService(StockService stockService) {
        this.stockService = stockService;
    }
    
    public double getMarketValue() {
        double marketValue = 0;
        for (Stock stock : stocks) {
            marketValue += stock.getQuantity() * stockService.getPrice(stock);
        }
        return marketValue;
    }
}
