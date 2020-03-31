/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package classes;

import beans.Student;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;

/**
 *
 * @author Denis Imeri
 */
public class StudentListModel extends AbstractListModel {
    
    private List<Student> students = new ArrayList<>();
    
    public void addStudent(Student st) throws Exception{
        if(students.contains(st)) {
            throw new Exception();
        }
        this.students.add(st);
        this.fireIntervalAdded(this, students.size()-1, students.size()-1);
    }
    
    public void removeStudent(int index){
        this.students.remove(index);
        this.fireIntervalRemoved(this, index, index);
    }
    
    public void removeStudents(List<Student> toRemove) {
        this.students.removeAll(toRemove);
        this.fireIntervalRemoved(this, 0, students.size());
    }
    
    public void updateStudent(int index, Student st) {
        this.students.set(index, st);
        this.fireContentsChanged(this, 0, students.size());
    }
    
    @Override
    public int getSize() {
        return students.size();
    }

    @Override
    public Student getElementAt(int index) {
        return students.get(index);
    }
    
}
