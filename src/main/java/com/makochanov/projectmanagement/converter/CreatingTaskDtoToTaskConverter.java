package com.makochanov.projectmanagement.converter;

import com.makochanov.projectmanagement.model.dto.CreatingTaskDto;
import com.makochanov.projectmanagement.model.entity.Task;
import org.springframework.core.convert.converter.Converter;

public class CreatingTaskDtoToTaskConverter implements Converter<CreatingTaskDto, Task> {

    @Override
    public Task convert(CreatingTaskDto source) {
        Task task = new Task();
        task.setName(source.getName());
        task.setDescription(source.getDescription());
        return task;
    }
}
