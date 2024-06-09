package com.sport.workoutapp.data

import com.sport.workoutapp.R
import com.sport.workoutapp.data.model.Day
import com.sport.workoutapp.data.model.Exercise
import com.sport.workoutapp.data.model.ExerciseType
import com.sport.workoutapp.ui.theme.Day1Color
import com.sport.workoutapp.ui.theme.Day2Color
import com.sport.workoutapp.ui.theme.Day3Color

fun getDaysData() = days

private val days: List<Day> = listOf(
    Day(
        title = "Плечи + трицепс + пресс",
        color = Day1Color,
        exercises = listOf(
            Exercise(
                title = "Разминка 5-10 мин",
                type = ExerciseType.WarmUp,
            ),
            Exercise(
                title = "1. Жим гантелей сидя",
                type = ExerciseType.Standard,
                sets = "3-4",
                reps = "10-15",
                gifResId = R.raw.seated_dumbbell_press,
            ),
            Exercise(
                title = "2. Отжимания от лавки сзади",
                type = ExerciseType.Standard,
                sets = "3-4",
                reps = "10-15",
                gifResId = R.raw.bench_pushups_from_behind
            ),
            Exercise(
                title = "3. Протяжка с гантелями",
                type = ExerciseType.Standard,
                sets = "3",
                reps = "10-15",
                gifResId = R.raw.dumbbell_pull
            ),
            Exercise(
                title = "4. Французский жим с гантелей",
                type = ExerciseType.Standard,
                sets = "3",
                reps = "10-15",
                gifResId = R.raw.french_press_with_dumbbells
            ),
            Exercise(
                title = "5. Махи гантелями в стороны",
                type = ExerciseType.Standard,
                sets = "3",
                reps = "10-15",
                gifResId = R.raw.swing_dumbbells_to_the_sides
            ),
            Exercise(
                title = "6. Французский жим с гантелями лёжа",
                type = ExerciseType.Standard,
                sets = "3",
                reps = "10-15",
                gifResId = R.raw.french_bench_press_with_dumbbells
            ),
            Exercise(
                title = "7. Скручивания лёжа на полу",
                type = ExerciseType.Standard,
                sets = "3",
                reps = "12-20",
                gifResId = R.raw.crunches
            ),
            Exercise(
                title = "Заминка 2-5 мин",
                type = ExerciseType.WarmDown
            ),
        )
    ),
    Day(
        title = "Ноги + бицепс",
        color = Day2Color,
        exercises = listOf(
            Exercise(
                title = "Разминка 5-10 мин",
                type = ExerciseType.WarmUp,
            ),
            Exercise(
                title = "1. Выпады с гантелями",
                type = ExerciseType.Standard,
                sets = "3",
                reps = "10-15",
                gifResId = R.raw.lunges_with_dumbbells
            ),
            Exercise(
                title = "2. Сгибания рук с гантелями сидя/стоя",
                type = ExerciseType.Standard,
                sets = "3",
                reps = "10-15",
                gifResId = R.raw.dumbbell_curls
            ),
            Exercise(
                title = "3. Становая тяга с гантелями",
                type = ExerciseType.Standard,
                sets = "3-4",
                reps = "10-15",
                gifResId = R.raw.deadlift_with_dumbbells
            ),
            Exercise(
                title = "4. Сгибания рук с гантелями «молот»",
                type = ExerciseType.Standard,
                sets = "3",
                reps = "10-15",
                gifResId = R.raw.hammer_dumbbell_curls
            ),
            Exercise(
                title = "5. Подъём таза лёжа одной ногой",
                type = ExerciseType.Standard,
                sets = "3",
                reps = "10-15",
                gifResId = R.raw.lifting_the_pelvis_while_lying_with_one_leg
            ),
            Exercise(
                title = "6. Сгибание руки сидя через колено",
                type = ExerciseType.Standard,
                sets = "3",
                reps = "10-15",
                gifResId = R.raw.bend_the_arm_while_sitting_over_the_knee
            ),
            Exercise(
                title = "7. Подъём на носки стоя",
                type = ExerciseType.Standard,
                sets = "3",
                reps = "12-20",
                gifResId = R.raw.standing_calf_raise
            ),
            Exercise(
                title = "Заминка 2-5 мин",
                type = ExerciseType.WarmDown
            ),
        )
    ),
    Day(
        title = "Грудь + спина + пресс",
        color = Day3Color,
        exercises = listOf(
            Exercise(
                title = "Разминка 5-10 мин",
                type = ExerciseType.WarmUp
            ),
            Exercise(
                title = "1. Жим гантелей лёжа",
                type = ExerciseType.Standard,
                sets = "3-4",
                reps = "10-15",
                gifResId = R.raw.dumbbell_bench_press
            ),
            Exercise(
                title = "2. Тяга одной гантели в наклоне",
                type = ExerciseType.Standard,
                sets = "3-4",
                reps = "10-15",
                gifResId = R.raw.bent_over_dumbbell_row
            ),
            Exercise(
                title = "3. Отжимания от пола широким хватом",
                type = ExerciseType.Standard,
                sets = "3",
                reps = "10-15",
                gifResId = R.raw.wide_grip_push_ups
            ),
            Exercise(
                title = "4. Тяга гантелей в наклоне",
                type = ExerciseType.Standard,
                sets = "3",
                reps = "10-15",
                gifResId = R.raw.bent_over_dumbbells_row
            ),
            Exercise(
                title = "5. Разводы с гантелями лёжа",
                type = ExerciseType.Standard,
                sets = "3",
                reps = "10-15",
                gifResId = R.raw.lying_dumbbell_flyes
            ),
            Exercise(
                title = "6. Пуловер с гантелей лёжа",
                type = ExerciseType.Standard,
                sets = "3",
                reps = "10-15",
                gifResId = R.raw.pullover_with_dumbbells_lying_down
            ),
            Exercise(
                title = "7. Подъём ног сидя на лавке",
                type = ExerciseType.Standard,
                sets = "3",
                reps = "12-20",
                gifResId = R.raw.leg_raise_while_sitting_on_a_bench
            ),
            Exercise(
                title = "Заминка 2-5 мин",
                type = ExerciseType.WarmDown
            ),
        )
    )
)