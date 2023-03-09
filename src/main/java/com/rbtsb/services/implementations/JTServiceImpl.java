package com.rbtsb.services.implementations;

import com.rbtsb.dto.CourierPriceCheckDto;
import com.rbtsb.dto.jt.JTPriceDataDto;
import com.rbtsb.services.JTService;
import lombok.RequiredArgsConstructor;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class JTServiceImpl implements JTService {

    private static final Logger logger = LoggerFactory.getLogger(JTServiceImpl.class);

    @Override
    public JTPriceDataDto getCourierPrice(CourierPriceCheckDto courierPriceCheckDto) throws IOException {
        Connection.Response response = Jsoup
                .connect("https://www.jtexpress.my/shipping-rates")
                .method(Connection.Method.GET)
                .referrer("https://www.jtexpress.my/shipping-rates")
                .execute();
        logger.info(response.statusMessage());

        Document serviceResponse = Jsoup
                .connect("https://www.jtexpress.my/shipping-rates")
                .referrer("https://www.jtexpress.my/shipping-rates")
                .userAgent("Mozilla/5.0")
                .timeout(10 * 1000)
                .cookies(response.cookies())
                .data("shipping_rates_type", "international")
                .data("sender_postcode", String.valueOf(courierPriceCheckDto.getOriginPostcode()))
                .data("destination_country", courierPriceCheckDto.getDestinationCountry())
                .data("receiver_postcode", String.valueOf(courierPriceCheckDto.getDestinationPostcode()))
                .data("length", String.valueOf(courierPriceCheckDto.getLength()))
                .data("width", String.valueOf(courierPriceCheckDto.getWidth()))
                .data("height", String.valueOf(courierPriceCheckDto.getHeight()))
                .data("weight", String.valueOf(courierPriceCheckDto.getParcelWeight()))
                .data("shipping_type", "EZ")
                .data("item_value", "")
                .data("_token", "1QJVq9RYvOxzgS09f43mWLMc5iL5Fi4hMyuysbLw")
                .method(Connection.Method.POST)
                .post();

        JTPriceDataDto jtPriceDataDto = new JTPriceDataDto();

        Elements tables = serviceResponse.select("table");
        if (tables.size() > 1) {
            Elements rows = tables.get(0).select("tr");

            if (rows.size() > 1) {
                Elements values = rows.get(0).select("td");
                String shippingRate = values.get(1).text();
                String totalRate = values.get(3).text();

                jtPriceDataDto.setShippingRate(Double.valueOf(shippingRate));
                jtPriceDataDto.setTotalRate(Double.valueOf(totalRate));
            }
        }
        return jtPriceDataDto;
    }
}
