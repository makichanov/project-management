package com.makichanov.projectmanagement.converter;

import com.makichanov.projectmanagement.model.entity.Task;
import com.makichanov.projectmanagement.model.dto.CreatingTaskDto;
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
