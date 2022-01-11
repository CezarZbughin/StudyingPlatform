call schedule_activities(4,1,"MONDAY",8,2,"TUESDAY",10,2,"FRIDAY",16,4,150);

select s.id, s.name, s.description, s.has_lecture, s.has_seminar, s.has_lab, s.date_start, s.date_end,
al1.students_capacity, al1.lecture_weight, al1.seminar_weight,
al1.lab_weight, al1.schedule_seminar_day, al1.schedule_lecture_day, al1.schedule_lab_day,
al1.schedule_seminar_hour, al1.schedule_lecture_hour, al1.schedule_lab_hour, al1.finished_schedule
from (select  * from teaching t where t.professor_id = 2) as al1 join subject s on al1.subject_id = s.id;