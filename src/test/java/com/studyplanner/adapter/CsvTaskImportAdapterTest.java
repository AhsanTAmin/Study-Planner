package com.studyplanner.adapter;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.studyplanner.domain.Task;
import com.studyplanner.factory.TaskFactory;

public class CsvTaskImportAdapterTest {

    @Test
    public void shouldConvertCsvLinesIntoTasks() {
        TaskImportAdapter adapter = new CsvTaskImportAdapter(new TaskFactory());

        List<String> csvLines = Arrays.asList(
            "Reading,Read Java chapter,2,2026-03-12,2",
            "Coding,Implement DAO layer,4,2026-03-10,1"
        );

        List<Task> tasks = adapter.importTasks(csvLines);

        assertEquals(2, tasks.size());
        assertEquals("Reading", tasks.get(0).getType());
        assertEquals("Read Java chapter", tasks.get(0).getName());
        assertEquals(2, tasks.get(0).getDurationHours());
        assertEquals("Coding", tasks.get(1).getType());
        assertEquals("Implement DAO layer", tasks.get(1).getName());
    }
}
