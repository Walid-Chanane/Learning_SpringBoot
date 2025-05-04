package com.example.cruddemo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.cruddemo.entity.Course;
import com.example.cruddemo.entity.Instructor;
import com.example.cruddemo.entity.InstructorDetails;
import com.example.cruddemo.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class AppDAOImpl implements AppDAO{
    
    //define field for entity manager
    private EntityManager entityManager;
    
    //inject entity manager using constructor injection
    @Autowired
    public AppDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor theInstructor) { //this is for saving an object(theInstructor) in the data base
    //creating an object is another matter, an will be handled in the main app or smwhere else
        entityManager.persist(theInstructor);
    }

    @Override
    public Instructor findInstructurById(int theId) {
        return entityManager.find(Instructor.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int theId) {
        //this will also delete the instructor details
        
        //retrieve the instructor
        Instructor tempInstructor = entityManager.find(Instructor.class, theId);

        //get the courses
        List<Course> courses = tempInstructor.getCourses();

        //break association of all courses for the instructor
        for(Course tempCourse : courses){
            tempCourse.setInstructor(null);
        }

        //delete the instructor
        entityManager.remove(tempInstructor);
    }

    @Override
    public InstructorDetails findInstructorDetailsById(int theId) {

        return entityManager.find(InstructorDetails.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailsById(int theId) {

        //find the instroctor details
        InstructorDetails tempInstructorDetails = entityManager.find(InstructorDetails.class, theId);

        //delete the instructor details which will also delete the instructor (cascading)
        entityManager.remove(tempInstructorDetails);
    }

    @Override
    public List<Course> findCoursesByIntructorId(int theId) {
        //it's different from find by id, here we select all the courses that have instructor_id = theId
        //in findById we serch directly theid=id when we and there is only one(unique)

        //create query to retrieve the courses with theId from the courses table
        TypedQuery<Course> query = entityManager.createQuery("from Course where instructor.id = :data"
                ,Course.class);
        query.setParameter("data", theId);

        //execute query
        List<Course> courses = query.getResultList();

        return courses;
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int theId) {

        //create query
        TypedQuery<Instructor> query = entityManager.createQuery("select i from Instructor i "
                        +"JOIN FETCH i.courses "
                        +"JOIN FETCH i.instructorDetails " //just delete the line if you dont need it
                        + "where i.id = :data", Instructor.class);
        query.setParameter("data", theId);

        //execute the query
        Instructor instructor = query.getSingleResult();

        return instructor;
    }

    @Override
    @Transactional
    public void update(Instructor theInstructor) {
        entityManager.merge(theInstructor);
    }

    @Override
    @Transactional
    public void update(Course theCourse) {
        entityManager.merge(theCourse);
    }

    @Override
    public Course findCourseById(int theId) {
        return entityManager.find(Course.class, theId);
    }

    @Override
    @Transactional
    public void deleteCourseById(int theId) {

        //retrieve the course
        Course tempCourse = entityManager.find(Course.class, theId);

        //delete the course
        entityManager.remove(tempCourse);
    }

    @Override
    @Transactional
    public void save(Course theCourse) {
        entityManager.persist(theCourse);
    }

    @Override
    public Course findCourseAndReviewsById(int theId) {

        //create query
        TypedQuery<Course> query = entityManager.createQuery("select c from Course c "
                                        + "JOIN FETCH c.reviews "
                                        + "where c.id = :data", Course.class);
        query.setParameter("data", theId);

        //execute query
        Course theCourse = query.getSingleResult();

        return theCourse;
    }

    @Override
    public Course findCourseAndStudentsByCourseId(int theId) {

        //create query 
        TypedQuery<Course> query = entityManager.createQuery("select c from Course c "
                                        + "JOIN FETCH c.students "
                                        + "where c.id = :data", Course.class);
        query.setParameter("data", theId);

        //execute query
        Course course = query.getSingleResult();

        return course;
    }

    @Override
    public Student findStudentAndCoursesByStudentId(int theId) {
        
        //create query
        TypedQuery<Student> query = entityManager.createQuery("select s from Student s "
                                        + "JOIN FETCH s.courses " // courses in student has the joinclomun
                                        // and inverse join column.. and there is no course_student class, 
                                        //so the courses field will handle the relation between the 2 tables
                                        + "where s.id = :data", Student.class);
        query.setParameter("data", theId);

        //execute the query
        Student student = query.getSingleResult();

        return student;
    }

    @Override
    @Transactional
    public void update(Student theStudent) {
        entityManager.merge(theStudent);
    }

    @Override
    @Transactional
    public void deleteStudentById(int theId) {

        //retrieve the student
        Student tempStudent = entityManager.find(Student.class, theId);

        //delete the student
        entityManager.remove(tempStudent);
    }
}
