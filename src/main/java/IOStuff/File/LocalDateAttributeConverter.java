package IOStuff.File;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.LocalDate;
import java.sql.Date;

@Converter(autoApply = true)
public class LocalDateAttributeConverter implements AttributeConverter<LocalDate, Date>
{
    @Override
    public Date convertToDatabaseColumn(LocalDate localDate)
    {
        return (localDate == null ? null : Date.valueOf(localDate));
    }

    @Override
    public LocalDate convertToEntityAttribute(Date sqlDSate)
    {
        return (sqlDSate == null ? null : sqlDSate.toLocalDate());
    }


}
