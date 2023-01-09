package com.lms.dao;

import com.lms.beans.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class BookDao {
    JdbcTemplate template;

    @Autowired
    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    public boolean save(Book book) {
        String sql = "INSERT INTO books(name, author, isbn, publisherName, pubDate, availableQty) VALUES('" + book.getName() + "', '" + book.getAuthor() + "', '" + book.getIsbn() + "', '" + book.getPublisherName() + "', '"+book.getPubDate()+"', "+book.getAvailableQty()+")";
        return template.update(sql) > 0;
    }

    public List<Book> booksList() {
        List<Book> list = template.query("SELECT * FROM books", new RowMapper<Book>() {

            public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
                Book book = new Book();

                book.setId(rs.getInt("id"));
                book.setName(rs.getString("name"));
                book.setAuthor(rs.getString("author"));
                book.setIsbn(rs.getString("isbn"));
                book.setPublisherName(rs.getString("publisherName"));
                book.setPubDate(rs.getString("pubDate"));
                book.setAvailableQty(rs.getInt("availableQty"));

                return book;
            }

        });

        return list;
    }

    public Book findById(int id) {
        String sql = "SELECT * FROM books where id=?";
        return template.queryForObject(sql, new Object[]{id},new BeanPropertyRowMapper<Book>(Book.class));
    }

    public Boolean update(Book book){
        String sql = "UPDATE books SET name='"+book.getName()+"', author='"+book.getAuthor()+"', isbn='"+book.getIsbn()+"', publisherName='"+book.getPublisherName()+"', pubDate='"+book.getPubDate()+"', availableQty='"+book.getAvailableQty()+"' WHERE id="+book.getId()+"";
        return template.update(sql) > 0;
    }

    public Boolean delete(int id){
        String sql = "DELETE FROM books WHERE id="+id+"";
        return template.update(sql) > 0;
    }
}