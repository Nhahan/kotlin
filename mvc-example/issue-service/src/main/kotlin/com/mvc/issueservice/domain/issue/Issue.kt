package com.mvc.issueservice.domain.issue

import com.mvc.issueservice.domain.BaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Issue (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    var userId: Long,
    var summary: String,
    var description: String,
    @Enumerated(EnumType.STRING)
    var type: IssueType,
    @Enumerated(EnumType.STRING)
    var priority: IssuePriority,
    @Enumerated(EnumType.STRING)
    var status: IssueStatus,
): BaseEntity()