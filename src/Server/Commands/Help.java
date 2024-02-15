package Server.Commands;

import Server.InfoSender;

import java.util.ArrayList;

public class Help implements Command{
    InfoSender infoSender;

    public Help(InfoSender infoSender){
        this.infoSender = infoSender;
    }

    @Override
    public void execute() {
        ArrayList<String> res = new ArrayList<>();
        res.add("help : вывести справку по доступным командам");
        res.add("info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)");
        res.add("show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        res.add("add {element, ввод элемента осущестлявется в следующих 5 строках} : добавить новый элемент в коллекцию");
        res.add("update id {element, ввод элемента осущестлявется в следующих 5 строках} : обновить значение элемента коллекции, id которого равен заданному");
        res.add("remove_by_id id : удалить элемент из коллекции по его id");
        res.add("clear : очистить коллекцию");
        res.add("save : сохранить коллекцию в файл");
        res.add("execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.");
        res.add("exit : завершить программу (без сохранения в файл)");
        res.add("add_if_max {element, ввод элемента осущестлявется в следующих 5 строках} : добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции");
        res.add("add_if_min {element, ввод элемента осущестлявется в следующих 5 строках} : добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции");
        res.add("history : вывести последние 7 команд (без их аргументов)");
        res.add("average_of_engine_power : вывести среднее значение поля enginePower для всех элементов коллекции");
        res.add("filter_contains_name name : вывести элементы, значение поля name которых содержит заданную подстроку");
        res.add("print_field_descending_engine_power : вывести значения поля enginePower всех элементов в порядке убывания");
        infoSender.sendMultiLines(res);
    }
}
