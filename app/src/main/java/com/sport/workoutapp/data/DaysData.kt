package com.sport.workoutapp.data

import com.sport.workoutapp.data.model.Day
import com.sport.workoutapp.data.model.Exercise
import com.sport.workoutapp.ui.theme.Day1Color
import com.sport.workoutapp.ui.theme.Day2Color
import com.sport.workoutapp.ui.theme.Day3Color

val days: List<Day> = listOf(
    Day(
        title = "Плечи + трицепс + пресс",
        color = Day1Color,
        exercises = listOf(
            Exercise("Разминка 5-10 мин"),
            Exercise("1. Жим гантелей сидя"),
            Exercise("2. Отжимания от лавки сзади"),
            Exercise("3. Протяжка с гантелями"),
            Exercise("4. Французский жим с гантелей"),
            Exercise("5. Махи гантелями в стороны"),
            Exercise("6. Французский жим с гантелями лёжа"),
            Exercise("7. Скручивания лёжа на полу"),
            Exercise("Заминка 2-5 мин"),
        )
    ),
    Day(
        title = "Ноги + бицепс",
        color = Day2Color,
        exercises = listOf(
            Exercise("Разминка 5-10 мин"),
            Exercise("1. Выпады с гантелями"),
            Exercise("2. Сгибания рук с гантелями сидя/стоя"),
            Exercise("3. Становая тяга с гантелями"),
            Exercise("4. Сгибания рук с гантелями «молот»"),
            Exercise("5. Подъём таза лёжа одной ногой"),
            Exercise("6. Сгибание руки сидя через колено"),
            Exercise("7. Подъём на носки стоя"),
            Exercise("Заминка 2-5 мин"),
        )
    ),
    Day(
        title = "Грудь + спина + пресс",
        color = Day3Color,
        exercises = listOf(
            Exercise("Разминка 5-10 мин"),
            Exercise("1. Жим гантелей лёжа"),
            Exercise("2. Тяга одной гантели в наклоне"),
            Exercise("3. Отжимания от пола широким хватом"),
            Exercise("4. Тяга гантелей в наклоне"),
            Exercise("5. Разводы с гантелями лёжа"),
            Exercise("6. Пуловер с гантелей лёжа"),
            Exercise("7. Подъём ног сидя на лавке"),
            Exercise("Заминка 2-5 мин"),
        )
    )
)