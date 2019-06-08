package com.universy.student.profile.model.converters;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.universy.student.profile.model.converters.exceptions.MailFormatException;
import com.universy.student.profile.model.StudentKey;

public class StudentKeyConverter implements DynamoDBTypeConverter<String, StudentKey> {

    private static final String MAIL_FORMAT_REGEX = "[a-z0-9!_.]+@[a-zA-Z_]+?(\\.[a-zA-Z]{1,3})+";
    private static final String WRONG_MAIL_FORMAT_MESSAGE = "Wrong mail format: ";

    @Override
    public String convert(StudentKey studentDataKey) {
        String mail = studentDataKey.getMail();

        if(mail.matches(MAIL_FORMAT_REGEX)){
            return studentDataKey.getMail();
        }
        throw new MailFormatException(WRONG_MAIL_FORMAT_MESSAGE + mail);
    }

    @Override
    public StudentKey unconvert(String mail) {

        if(mail.matches(MAIL_FORMAT_REGEX)){
            return new StudentKey(mail);
        }
        throw new MailFormatException(WRONG_MAIL_FORMAT_MESSAGE + mail);
    }
}