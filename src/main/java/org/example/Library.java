package org.example;

import org.apache.log4j.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Library {

    static Logger log = Logger.getLogger(Library.class.getName());


    public void addBook(Book book,int stud_id){

        SessionFactory sessionfactory = buildsessionfactory(Book.class);
        Session session = sessionfactory.openSession();


        if (session.find(Student.class, stud_id) == null){
            log.debug("Student is not in database, not a valid library user");
        }
        else{
            try{
                session.persist(book);
                book.book_count++;

                log.info("book created");
            }
            catch(Exception e){
                log.debug("Unable to find student:",e);
            }
        }


        sessionfactory.close();
    }

    public Book borrowBook(Student stud,int book_id){

        SessionFactory sessionfactory = buildsessionfactory(Book.class);
        Session session = sessionfactory.openSession();
        Book givenBook = new Book();

        if ((session.find(Student.class, stud.getId()) == null)&&(stud.isBorrowed()))
        {
            log.debug("Student is not in database, not a valid library user");
        }else{
            try{
                givenBook = session.find(Book.class,book_id);
                givenBook.book_count--;

                log.info("book given");
            }
            catch(Exception e){
                log.debug("Unable to find student:",e);
            }
        }
        sessionfactory.close();
        return givenBook;
    }


    public void returnBook(Book book,String stud_id){

        SessionFactory sessionfactory = buildsessionfactory(Book.class);
        Session session = sessionfactory.openSession();


        if (session.find(Student.class, stud_id) == null){
            log.debug("Student is not in database, not a valid library user");
        }
        else{
            try{
                session.update(book);

                log.info("book returned by:"+stud_id);
            }
            catch(Exception e){
                log.debug("Unable to find student:",e);
            }
        }

        sessionfactory.close();
    }




    public void addstud(Student stud ){

        SessionFactory sessionfactory = buildsessionfactory(Book.class);
        Session session = sessionfactory.openSession();

        try{
            session.persist(stud);
            log.info("student :"+ stud.getStud_name()+"added");
        }
        catch(Exception e){
            log.trace("unable to add student",e);
        }



        sessionfactory.close();
    }




    public static SessionFactory buildsessionfactory(Class clazz){
        return new Configuration()
                .configure()
                .addAnnotatedClass(clazz)
                .buildSessionFactory();
    }
}


