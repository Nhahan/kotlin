package com.example.fcboard.common.annotation

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Retention(AnnotationRetention.RUNTIME) // 번들-패키지된 클래스를 포함해 런타임에도 유지됩니다.
@Target(AnnotationTarget.CLASS) // 이 어노테이션을 클래스에만 적용할 수 있게 합니다.
@Service
@Transactional(readOnly = true)
annotation class CustomService
