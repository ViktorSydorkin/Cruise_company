package Controller.Validator;

public interface Regexes {
    String NAME = "^([A-ZА-ЯЄІЇ])((['][A-Zа-яєії])?)([a-zа-яєії]+)([-][A-ZА-ЯІЇ]((['][A-Zа-яєії])?)[a-zа-яєії']+)?";
    String TITLE_ENG = "^[A-Z][a-z' ]{1,}";
    String TITLE_UA = "^[А-ЯЄІЇ][а-яєії' ]{1,}";
    String EMAIL = "\\w{3,}@([a-z]+\\.[a-z]+){1}";
    String PASSWORD = ".{6,45}";
}
