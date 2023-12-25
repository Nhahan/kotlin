package com.mvc.issueservice.domain

enum class IssueType {
    BUG, TASK;

    companion object {
        operator fun invoke(type: String) = valueOf(type.uppercase())
    }
}

enum class IssuePriority {
    LOW, MEDIUM, HIGH;
    companion object {
        operator fun invoke(type: String) = IssueType.valueOf(type.uppercase())
    }
}

enum class IssueStatus {
    TODO, IN_PROGRESS, RESOLVED;
    companion object {
        operator fun invoke(type: String) = IssueType.valueOf(type.uppercase())
    }
}