package com.ps.todoapp.entity.appinfo;

import lombok.Builder;

import java.time.Instant;

@Builder
public record AppInfoDTO(Instant timestamp,
                         long totalTasks,
                         long highPriorityTasks,
                         long mediumPriorityTasks,
                         long lowPriorityTasks,
                         long totalTasksRequests,
                         long getRequests,
                         long postRequests,
                         long putRequests,
                         long patchRequest,
                         long deleteRequests) {
}
