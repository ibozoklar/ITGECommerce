import React from "react";
import HomeContent from "./homeContent";


export default function Content({ selectedSubMenu }) {
  let content = null;

  switch (selectedSubMenu) {
    case "all-users":
      content = (
        <div>
          <h2>Tüm Kullanıcılar</h2>
          <p>Bu alanda tüm kullanıcılar listelenir.</p>
        </div>
      );
      break;
    case "add-user":
      content = <AddUser />
      break;
    case "edit-user":
      content = (
        <div>
          <h2>Kullanıcı Düzenle</h2>
          <p>Bu alanda var olan kullanıcılar düzenlenebilir.</p>
        </div>
      );
      break;
    case "all-surveys":
      content = <ListSurveys />
      break;
    case "create-survey":
      content = <CreateSurvey/>
      break;
    case "send-survey":
      content = (
        <div>
          <h2>Anket Gönder</h2>
          <p>Bu alanda var olan anketler gönderilebilir.</p>
        </div>
      );
      break;
    case "survey-tags":
      content = <SurveyTag />
      break;
    case "list-questions":
      content = (
        <div>
          <h2>Soruları Listeleme</h2>
          <p>Bu alanda tüm sorular listelenir.</p>
        </div>
      );
      break;
    case "add-question":
      content = (
        <div>
          <h2>Soru Ekleme</h2>
          <p>Bu alanda soru eklenir.</p>
        </div>
      );
      break;
    case "question-tags":
      content = <QuestionTag />
      break;
    case "question-types":
      content = <QuestionType />
      break;
    case "all-students":
      content = (
        <div>
          <h2>Tüm Öğrenciler</h2>
          <p>Bu alanda tüm öğrenciler listelenir.</p>
        </div>
      );
      break;
    case "all-trainers":
      content = (
        <div>
          <h2>Tüm Eğitmenler</h2>
          <p>Bu alanda tüm eğitmenler listelenir.</p>
        </div>
      );
      break;
    case "classroom-tags":
      content = <ClassroomTag />
      break;
    case "assign-classroom":
      content = <AssignSurvey />
      break;
    case "reporting-1":
      content = (
        <div>
          <h2>Raporlama-1 İşlemi</h2>
          <p>Bu alanda raporlama-1 işlemi yapılır.</p>
        </div>
      );
      break;
    case "reporting-2":
      content = (
        <div>
          <h2>Raporlama-2 İşlemi</h2>
          <p>Bu alanda raporlama-2 işlemi yapılır.</p>
        </div>
      );
      break;
    case "reporting-3":
      content = (
        <div>
          <h2>Raporlama-3 İşlemi</h2>
          <p>Bu alanda raporlama-3 işlemi yapılır.</p>
        </div>
      );
      break;
    default:
      content = <HomeContent />
  }

  return <div className="content">{content}</div>;
}
