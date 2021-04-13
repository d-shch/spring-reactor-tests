package com.example.sr.extensions

import io.qameta.allure.Allure
import reactor.test.StepVerifier

@Throws(Throwable::class)
fun step(title: String, action: () -> Any?) = Allure.step(title, Allure.ThrowableRunnableVoid { action() })

@Throws(Throwable::class)
fun <T> step(title: String, action: () -> T): T = Allure.step(title, Allure.ThrowableRunnable { action() })

fun <T> StepVerifier.Step<T>.assertNextStep(title: String, action: (t: T) -> Any): StepVerifier.Step<T> =
        Allure.step(title, Allure.ThrowableRunnable {
            assertNext {
                action(it)
            }
        })

fun <T> StepVerifier.Step<T>.expectErrorStep(title: String, clazz: Class<out Throwable>): StepVerifier =
        Allure.step(title, Allure.ThrowableRunnable {
            expectError(clazz)
        })