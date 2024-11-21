package com.sport.workoutapp.data

import androidx.compose.ui.graphics.toArgb
import com.sport.workoutapp.R
import com.sport.workoutapp.WorkOutApplication.Companion.realm
import com.sport.workoutapp.data.model.Day
import com.sport.workoutapp.data.model.Exercise
import com.sport.workoutapp.data.model.ExerciseType
import com.sport.workoutapp.ui.theme.Day1Color
import com.sport.workoutapp.ui.theme.Day2Color
import com.sport.workoutapp.ui.theme.Day3Color
import io.realm.kotlin.UpdatePolicy
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

object Repository {

    fun createSampleEntries(scope: CoroutineScope) {
        scope.launch {
            val day1 = Day().apply {
                title = "Плечи + трицепс + пресс"
                color = Day1Color.toArgb()
                exercises.addAll(
                    listOf(
                        Exercise().apply {
                            title = "Разминка 5-10 мин"
                            type = ExerciseType.WarmUp.name
                        },
                        Exercise().apply {
                            title = "Жим гантелей сидя"
                            type = ExerciseType.Standard.name
                            sets = "3-4"
                            reps = "10-15"
                            gifResId = R.raw.seated_dumbbell_press
                        },
                        Exercise().apply {
                            title = "Отжимания от лавки сзади"
                            type = ExerciseType.Standard.name
                            sets = "3-4"
                            reps = "10-15"
                            gifResId = R.raw.bench_pushups_from_behind
                        },
                        Exercise().apply {
                            title = "Протяжка с гантелями"
                            type = ExerciseType.Standard.name
                            sets = "3"
                            reps = "10-15"
                            gifResId = R.raw.dumbbell_pull
                        },
                        Exercise().apply {
                            title = "Французский жим с гантелей"
                            type = ExerciseType.Standard.name
                            sets = "3"
                            reps = "10-15"
                            gifResId = R.raw.french_press_with_dumbbells
                        },
                        Exercise().apply {
                            title = "Махи гантелями в стороны"
                            type = ExerciseType.Standard.name
                            sets = "3"
                            reps = "10-15"
                            gifResId = R.raw.swing_dumbbells_to_the_sides
                        },
                        Exercise().apply {
                            title = "Французский жим с гантелями лёжа"
                            type = ExerciseType.Standard.name
                            sets = "3"
                            reps = "10-15"
                            gifResId = R.raw.french_bench_press_with_dumbbells
                        },
                        Exercise().apply {
                            title = "Скручивания лёжа на полу"
                            type = ExerciseType.Standard.name
                            sets = "3"
                            reps = "12-20"
                            gifResId = R.raw.crunches
                        },
                        Exercise().apply {
                            title = "Заминка 2-5 мин"
                            type = ExerciseType.WarmDown.name
                        }
                    )
                )
            }

            val day2 = Day().apply {
                title = "Ноги + бицепс"
                color = Day2Color.toArgb()
                exercises.addAll(
                    listOf(
                        Exercise().apply {
                            title = "Разминка 5-10 мин"
                            type = ExerciseType.WarmUp.name
                        },
                        Exercise().apply {
                            title = "Выпады с гантелями"
                            type = ExerciseType.Standard.name
                            sets = "3"
                            reps = "10-15"
                            gifResId = R.raw.lunges_with_dumbbells
                        },
                        Exercise().apply {
                            title = "Сгибания рук с гантелями сидя/стоя"
                            type = ExerciseType.Standard.name
                            sets = "3"
                            reps = "10-15"
                            gifResId = R.raw.dumbbell_curls
                        },
                        Exercise().apply {
                            title = "Становая тяга с гантелями"
                            type = ExerciseType.Standard.name
                            sets = "3-4"
                            reps = "10-15"
                            gifResId = R.raw.deadlift_with_dumbbells
                        },
                        Exercise().apply {
                            title = "Сгибания рук с гантелями «молот»"
                            type = ExerciseType.Standard.name
                            sets = "3"
                            reps = "10-15"
                            gifResId = R.raw.hammer_dumbbell_curls
                        },
                        Exercise().apply {
                            title = "Подъём таза лёжа одной ногой"
                            type = ExerciseType.Standard.name
                            sets = "3"
                            reps = "10-15"
                            gifResId = R.raw.lifting_the_pelvis_while_lying_with_one_leg
                        },
                        Exercise().apply {
                            title = "Сгибание руки сидя через колено"
                            type = ExerciseType.Standard.name
                            sets = "3"
                            reps = "10-15"
                            gifResId = R.raw.bend_the_arm_while_sitting_over_the_knee
                        },
                        Exercise().apply {
                            title = "Подъём на носки стоя"
                            type = ExerciseType.Standard.name
                            sets = "3"
                            reps = "12-20"
                            gifResId = R.raw.standing_calf_raise
                        },
                        Exercise().apply {
                            title = "Заминка 2-5 мин"
                            type = ExerciseType.WarmDown.name
                        }
                    )
                )
            }

            val day3 = Day().apply {
                title = "Грудь + спина + пресс"
                color = Day3Color.toArgb()
                exercises.addAll(
                    listOf(
                        Exercise().apply {
                            title = "Разминка 5-10 мин"
                            type = ExerciseType.WarmUp.name
                        },
                        Exercise().apply {
                            title = "Жим гантелей лёжа"
                            type = ExerciseType.Standard.name
                            sets = "3-4"
                            reps = "10-15"
                            gifResId = R.raw.dumbbell_bench_press
                        },
                        Exercise().apply {
                            title = "Тяга одной гантели в наклоне"
                            type = ExerciseType.Standard.name
                            sets = "3-4"
                            reps = "10-15"
                            gifResId = R.raw.bent_over_dumbbell_row
                        },
                        Exercise().apply {
                            title = "Отжимания от пола широким хватом"
                            type = ExerciseType.Standard.name
                            sets = "3"
                            reps = "10-15"
                            gifResId = R.raw.wide_grip_push_ups
                        },
                        Exercise().apply {
                            title = "Тяга гантелей в наклоне"
                            type = ExerciseType.Standard.name
                            sets = "3"
                            reps = "10-15"
                            gifResId = R.raw.bent_over_dumbbells_row
                        },
                        Exercise().apply {
                            title = "Разводы с гантелями лёжа"
                            type = ExerciseType.Standard.name
                            sets = "3"
                            reps = "10-15"
                            gifResId = R.raw.lying_dumbbell_flyes
                        },
                        Exercise().apply {
                            title = "Пуловер с гантелей лёжа"
                            type = ExerciseType.Standard.name
                            sets = "3"
                            reps = "10-15"
                            gifResId = R.raw.pullover_with_dumbbells_lying_down
                        },
                        Exercise().apply {
                            title = "Подъём ног сидя на лавке"
                            type = ExerciseType.Standard.name
                            sets = "3"
                            reps = "12-20"
                            gifResId = R.raw.leg_raise_while_sitting_on_a_bench
                        },
                        Exercise().apply {
                            title = "Заминка 2-5 мин"
                            type = ExerciseType.WarmDown.name
                        }
                    )
                )
            }

            realm.write {
                copyToRealm(day1, updatePolicy = UpdatePolicy.ALL)
                copyToRealm(day2, updatePolicy = UpdatePolicy.ALL)
                copyToRealm(day3, updatePolicy = UpdatePolicy.ALL)
            }
        }
    }
}