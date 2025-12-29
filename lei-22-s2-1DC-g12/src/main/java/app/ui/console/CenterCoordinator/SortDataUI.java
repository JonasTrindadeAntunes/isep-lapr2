package app.ui.console.CenterCoordinator;

import app.controller.CenterCoordinator.SortDataController;
import app.domain.shared.MessageBundle;
import app.ui.console.utils.Utils;

public class SortDataUI implements Runnable{

    private SortDataController m_controller;

    public SortDataUI(){
        this.m_controller = new SortDataController();
    }

    public void run(){
        System.out.println("Sorting Algorithms");

        if (!m_controller.checkLogin()) {
            System.out.println(MessageBundle.getString("unauthorizeduser"));
            return;
        }

        int typeOfData =  Utils.showAndSelectIndex(m_controller.selectTypeOfDataToSort(),"Select type of data to sort and sorting technique");

        while(typeOfData >= 0 && typeOfData < 4 ) {

                for (String s : m_controller.sortBy(typeOfData))
                    System.out.println(s);

            typeOfData =  Utils.showAndSelectIndex(m_controller.selectTypeOfDataToSort(),"Select type of data to sort and sorting technique");
        }

    }
}
