package uz.pdp.online.lesson_7_news_site.entity.enums;

public enum Huquq {// PERMISSION
    /*TIZIMGA BOG'LIQ*/
    ADD_USER, //ADMIN TOMONDAN QO'SHISH
    EDIT_USER, //USERNI O'ZI EMAS BOSHQA TOMONDAN EDIT QILINISHI
    DELETE_USER,
    VIEW_USERS,
    ADD_LAVOZIM,
    EDIT_LAVOZIM,
    DELETE_LAVOZIM,
    VIEW_LAVOZIMLAR,

    /*MAQOLAGA BOG'LIQ*/
    ADD_POST,
    EDIT_POST,
    DELETE_POST,

    /*COMMENT NUQTAI NAZARIDAN*/
    ADD_COMMENT,
    EDIT_COMMENT, // USERNI COMMENTINI O'ZIDAN BOSHQA HECH KIM O'ZGARTIRISH HUQUQIGA EGA EMAS
    DELETE_COMMNET, // TIZIMDAGI ODAM UCHUN
    DELETE_MY_COMMENT, // USER UCHUN


}
