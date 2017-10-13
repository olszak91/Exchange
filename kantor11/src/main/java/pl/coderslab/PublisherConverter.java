package pl.coderslab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import pl.coderslab.entity.Publisher;

public class PublisherConverter implements Converter<String, Publisher> {
    @Autowired
    private  PublisherDao publisherDao;
    @Override
    public Publisher convert(String source) {
    	Publisher group = publisherDao.findById(Integer.parseInt(source));
        return  group;
    }
}