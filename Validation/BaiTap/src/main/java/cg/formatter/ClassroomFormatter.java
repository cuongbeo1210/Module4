package cg.formatter;

import cg.model.Classroom;
import cg.service.IClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import java.util.Locale;
import java.util.Optional;

public class ClassroomFormatter implements Formatter<Classroom> {
    private IClassroomService iClassroomService;

    @Autowired
    public ClassroomFormatter(IClassroomService iClassroomService) {
        this.iClassroomService = iClassroomService;
    }

    @Override
    public Classroom parse(String text, Locale locale) {
        Optional<Classroom> classroomOptional = iClassroomService.findById(Integer.parseInt(text));
        return classroomOptional.orElse(null);
    }

    @Override
    public String print(Classroom object, Locale locale) {
        return "[" + object.getId() + ", " + object.getName() + "]";
    }
}
