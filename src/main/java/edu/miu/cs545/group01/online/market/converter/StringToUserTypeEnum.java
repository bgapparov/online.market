package edu.miu.cs545.group01.online.market.converter;

import edu.miu.cs545.group01.online.market.domain.enums.UserType;
import org.springframework.core.convert.converter.Converter;

public class StringToUserTypeEnum implements Converter<String, UserType> {
    @Override
    public UserType convert(String source) {
        return UserType.valueOf(source.toUpperCase());
    }
}
