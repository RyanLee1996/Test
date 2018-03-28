package ryan.hibernate;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class CourseEntity implements Serializable{
    private int id;
    private String name;
    private Set<StudentEntity> courseByStudent = new HashSet<StudentEntity>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<StudentEntity> getCourseByStudent() {
        return courseByStudent;
    }

    public void setCourseByStudent(Set<StudentEntity> courseByStudent) {
        this.courseByStudent = courseByStudent;
    }
}
