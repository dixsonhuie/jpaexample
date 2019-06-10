package com.gigaspaces.demo.common;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;



public class PhoneUserType implements UserType {

    @Override
    public int[] sqlTypes() {
        return new int[]{Types.VARCHAR};
    }

    @Override
    public Class returnedClass() {
        return Phone.class;
    }

    @Override
    public boolean equals(Object x, Object y) throws HibernateException {
        return ObjectUtils.nullSafeEquals(x, y);
    }

    @Override
    public int hashCode(Object x) throws HibernateException {
        if (x!=null)
            return x.hashCode();
        else
            return 0;
    }

    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, SharedSessionContractImplementor session, Object owner) throws HibernateException, SQLException {
        Phone phone=null;

        String nameVal = rs.getString(names[0]);
        if(nameVal !=null){
            phone=new Phone();


            String li[] = nameVal.split("-");

            int index = 0;
            if( nameVal.startsWith("+") ) {
                phone.setCountryCode(li[index].replace("+", ""));
                index += 1;
            }

            phone.setAreaCode(li[index]);
            index += 1;

            String ph = "";
            for( int i = index; i < li.length; i++) {
                if( i == index)
                    ph += li[i];
                else
                    ph += "-" + li[i];
            }
            phone.setPhoneNum(ph);
        }
        return phone;
    }

    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session) throws HibernateException, SQLException {
        if (value==null) {
            st.setNull(index, Types.VARCHAR);
        }else{
            st.setString(index, ((Phone)value).toString());
        }
    }

    @Override
    public Object deepCopy(Object value) throws HibernateException {
        if (value==null)
            return null;
        else{
            Phone newObj = new Phone();
            Phone existObj =(Phone) value;
            newObj.setCountryCode(existObj.getCountryCode());
            newObj.setAreaCode(existObj.getAreaCode());
            newObj.setPhoneNum(existObj.getPhoneNum());

            return newObj;
        }
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Serializable disassemble(Object value) throws HibernateException {
        Object deepCopy = deepCopy(value);

        if(!(deepCopy instanceof Serializable))
            return (Serializable) deepCopy;

        return null;
    }

    @Override
    public Object assemble(Serializable cached, Object owner) throws HibernateException {
        return deepCopy(cached);
    }

    @Override
    public Object replace(Object original, Object target, Object owner) throws HibernateException {
        return deepCopy(original);
    }
}
