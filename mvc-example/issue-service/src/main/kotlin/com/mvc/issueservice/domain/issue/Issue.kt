package com.mvc.issueservice.domain.issue

import com.mvc.issueservice.domain.BaseEntity
import com.mvc.issueservice.domain.comment.Comment
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany

@Entity
class Issue (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    var userId: Long,
    var summary: String,
    var description: String,
    @OneToMany(mappedBy = "issue")
    var comments: MutableList<Comment> = mutableListOf(),
    @Enumerated(EnumType.STRING)
    var type: IssueType,
    @Enumerated(EnumType.STRING)
    var priority: IssuePriority,
    @Enumerated(EnumType.STRING)
    var status: IssueStatus,
): BaseEntity()