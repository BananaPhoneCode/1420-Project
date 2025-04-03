package com.example._1420project;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.List;
import java.util.ResourceBundle;
import java.util.ArrayList;

public class EventController implements Initializable {

    ZonedDateTime dateFocus;
    ZonedDateTime today;

    @FXML
    private Text Year;
    @FXML
    private Text Month;
    @FXML
    private FlowPane Calendar;

    private static final Duration ANIMATION_DURATION = Duration.seconds(0.5);
    @FXML
    ImageView menu;
    @FXML
    ImageView DashManagement;
    @FXML
    ImageView SubManagement;
    @FXML
    ImageView CouManagement;
    @FXML
    ImageView StuManagement;
    @FXML
    ImageView FacManagement;
    @FXML
    ImageView EveManagement;
    @FXML
    private AnchorPane pane1, pane2, CalPane, ListPane, EvePane, AddEvePane, EditEvePane, DeleteEvePane;

    @FXML
    private TableView<Event> EventTable = new TableView<>();
    @FXML
    private TableColumn<Event, String> EventName = new TableColumn<>();
    @FXML
    private TableColumn<Event, String> EventCode = new TableColumn<>();
    @FXML
    private TableColumn<Event, String> EventDescription = new TableColumn<>();
    @FXML
    private TableColumn<Event, String> EventLocation = new TableColumn<>();
    @FXML
    private TableColumn<Event, String> EventDateTime = new TableColumn<>();
    @FXML
    private TableColumn<Event, String> EventCapacity = new TableColumn<>();
    @FXML
    private TableColumn<Event, String> EventCost = new TableColumn<>();
    Button EventAdd = new Button();
    @FXML
    TextField EventCodeTxt = new TextField();
    @FXML
    TextField EventNameTxt = new TextField();
    @FXML
    TextField EveDescTxt = new TextField();
    @FXML
    TextField EveLocaTxt = new TextField();
    @FXML
    TextField EveDTTxt = new TextField();
    @FXML
    TextField EveCapTxt = new TextField();
    @FXML
    TextField EveCostTxt = new TextField();
    @FXML
    TextField EventCodeEditTxt = new TextField();
    @FXML
    TextField EventNameEditTxt = new TextField();
    @FXML
    TextField EveDescEditTxt = new TextField();
    @FXML
    TextField EveLocaEditTxt = new TextField();
    @FXML
    TextField EveDTEditTxt = new TextField();
    @FXML
    TextField EveCapEditTxt = new TextField();
    @FXML
    TextField EveCostEditTxt = new TextField();
    @FXML
    TextField EventSearchBar = new TextField();
    @FXML
    AnchorPane EventAddButtonMenu, EventDeleteButtonMenu, EventEditButtonMenu;

    ObservableList<Event> EventTableViewList = FXCollections.observableArrayList();
    ObservableList<Event> EventSearchTableViewList = FXCollections.observableArrayList();
    ArrayList<Event> currentEventList = new EditEventList().Generate();

    public EventController() throws IOException {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Initialize Admin Buttons and Event List
        EventTableViewList.addAll(currentEventList);
        EventCode.setCellValueFactory(new PropertyValueFactory<Event, String>("EventCode"));
        EventName.setCellValueFactory(new PropertyValueFactory<Event, String>("EventName"));
        EventDescription.setCellValueFactory(new PropertyValueFactory<Event, String>("EventDescription"));
        EventLocation.setCellValueFactory(new PropertyValueFactory<Event, String>("EventLocation"));
        EventDateTime.setCellValueFactory(new PropertyValueFactory<Event, String>("EventDateNTime"));
        EventCapacity.setCellValueFactory(new PropertyValueFactory<Event, String>("EventCapacity"));
        EventCost.setCellValueFactory(new PropertyValueFactory<Event, String>("EventCost"));
        EventTable.setItems(EventTableViewList);

        // ** Calendar initializations **
        dateFocus = ZonedDateTime.now();
        today = ZonedDateTime.now();
        drawCalendar();

        pane1.setVisible(false);

        // Initial animations
        createFadeTransition(pane1, 1, 0, ANIMATION_DURATION).play();
        createTranslateTransition(pane2, -600, ANIMATION_DURATION).play();

        // Menu click event
        menu.setOnMouseClicked(event -> {
            pane1.setVisible(true);
            createFadeTransition(pane1, 0, 0.15, ANIMATION_DURATION).play();
            createTranslateTransition(pane2, +600, ANIMATION_DURATION).play();
        });

        // Pane1 click event
        pane1.setOnMouseClicked(event -> {
            FadeTransition fadeTransition = createFadeTransition(pane1, 0.15, 0, ANIMATION_DURATION);
            fadeTransition.play();
            fadeTransition.setOnFinished(event1 -> pane1.setVisible(false));
            createTranslateTransition(pane2, -600, ANIMATION_DURATION).play();
        });


    }

    @FXML
    void backOneMonth(ActionEvent event) {
        dateFocus = dateFocus.minusMonths(1);
        Calendar.getChildren().clear();
        drawCalendar();
    }

    @FXML
    void forwardOneMonth(ActionEvent event) {
        dateFocus = dateFocus.plusMonths(1);
        Calendar.getChildren().clear();
        drawCalendar();
    }

    private void drawCalendar() {
        Year.setText(String.valueOf(dateFocus.getYear()));
        Month.setText(String.valueOf(dateFocus.getMonth()));

        double calendarWidth = Calendar.getPrefWidth();
        double calendarHeight = Calendar.getPrefHeight();
        double strokeWidth = 1;
        double spacingH = Calendar.getHgap();
        double spacingV = Calendar.getVgap();

        //List of activities for a given month
        Map<Integer, List<CalendarActivity>> calendarActivityMap = getCalendarActivitiesMonth(dateFocus);

        int monthMaxDate = dateFocus.getMonth().maxLength();
        //Check for leap year
        if (dateFocus.getYear() % 4 != 0 && monthMaxDate == 29) {
            monthMaxDate = 28;
        }
        int dateOffset = ZonedDateTime.of(dateFocus.getYear(), dateFocus.getMonthValue(), 1, 0, 0, 0, 0, dateFocus.getZone()).getDayOfWeek().getValue();

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                StackPane stackPane = new StackPane();

                Rectangle rectangle = new Rectangle();
                rectangle.setFill(Color.TRANSPARENT);
                rectangle.setStroke(Color.BLACK);
                rectangle.setStrokeWidth(strokeWidth);
                double rectangleWidth = (calendarWidth / 7) - strokeWidth - spacingH - 2;
                rectangle.setWidth(rectangleWidth);
                double rectangleHeight = (calendarHeight / 6) - strokeWidth - spacingV - 5;
                rectangle.setHeight(rectangleHeight);
                stackPane.getChildren().add(rectangle);

                int calculatedDate = (j + 1) + (7 * i);
                if (calculatedDate > dateOffset) {
                    int currentDate = calculatedDate - dateOffset;
                    if (currentDate <= monthMaxDate) {
                        Text date = new Text(String.valueOf(currentDate));
                        double textTranslationY = -(rectangleHeight / 2) * 0.75;
                        date.setTranslateY(textTranslationY);
                        stackPane.getChildren().add(date);

                        List<CalendarActivity> calendarActivities = calendarActivityMap.get(currentDate);
                        if (calendarActivities != null) {
                            createCalendarActivity(calendarActivities, rectangleHeight, rectangleWidth, stackPane);
                        }
                    }
                    if (today.getYear() == dateFocus.getYear() && today.getMonth() == dateFocus.getMonth() && today.getDayOfMonth() == currentDate) {
                        rectangle.setStroke(Color.BLUE);
                    }
                }
                Calendar.getChildren().add(stackPane);
            }
        }
    }

    private void createCalendarActivity(List<CalendarActivity> calendarActivities, double rectangleHeight, double rectangleWidth, StackPane stackPane) {
        VBox calendarActivityBox = new VBox();
        for (int k = 0; k < calendarActivities.size(); k++) {
            if (k >= 2) {
                Text moreActivities = new Text("...");
                calendarActivityBox.getChildren().add(moreActivities);
                moreActivities.setOnMouseClicked(mouseEvent -> {
                    //On ... click print all activities for given date
                    System.out.println(calendarActivities);
                });
                break;
            }
            Text text = new Text(calendarActivities.get(k).getStudentName() + ", " + calendarActivities.get(k).getDate().toLocalTime());
            calendarActivityBox.getChildren().add(text);
            text.setOnMouseClicked(mouseEvent -> {
                //On Text clicked
                System.out.println(text.getText());
            });
        }
        calendarActivityBox.setTranslateY((rectangleHeight / 2) * 0.20);
        calendarActivityBox.setMaxWidth(rectangleWidth * 0.8);
        calendarActivityBox.setMaxHeight(rectangleHeight * 0.65);
        calendarActivityBox.setStyle("-fx-background-color:GRAY");
        stackPane.getChildren().add(calendarActivityBox);
    }

    private Map<Integer, List<CalendarActivity>> createCalendarMap(List<CalendarActivity> calendarActivities) {
        Map<Integer, List<CalendarActivity>> calendarActivityMap = new HashMap<>();

        for (CalendarActivity activity : calendarActivities) {
            int activityDate = activity.getDate().getDayOfMonth();
            if (!calendarActivityMap.containsKey(activityDate)) {
                calendarActivityMap.put(activityDate, List.of(activity));
            } else {
                List<CalendarActivity> OldListByDate = calendarActivityMap.get(activityDate);

                List<CalendarActivity> newList = new ArrayList<>(OldListByDate);
                newList.add(activity);
                calendarActivityMap.put(activityDate, newList);
            }
        }
        return calendarActivityMap;
    }

    private Map<Integer, List<CalendarActivity>> getCalendarActivitiesMonth(ZonedDateTime dateFocus) {
        List<CalendarActivity> calendarActivities = new ArrayList<>();
        int year = dateFocus.getYear();
        int month = dateFocus.getMonth().getValue();

        Random random = new Random();
        for (int i = 0; i < 50; i++) {
            ZonedDateTime time = ZonedDateTime.of(year, month, random.nextInt(27) + 1, 16, 0, 0, 0, dateFocus.getZone());
            calendarActivities.add(new CalendarActivity(time, "Test", 111111));
        }

        return createCalendarMap(calendarActivities);
    }

    // Start Of Menu Controls #######################################################################
    private FadeTransition createFadeTransition(Node node, double from, double to, Duration duration) {
        FadeTransition fadeTransition = new FadeTransition(duration, node);
        fadeTransition.setFromValue(from);
        fadeTransition.setToValue(to);
        return fadeTransition;
    }

    private TranslateTransition createTranslateTransition(Node node, double byX, Duration duration) {
        TranslateTransition translateTransition = new TranslateTransition(duration, node);
        translateTransition.setByX(byX);
        return translateTransition;
    }

    public void ToCPane() {
        CalPane.setVisible(true);
        ListPane.setVisible(false);
        EvePane.setVisible(false);
    }

    public void ToEvePane() {
        EvePane.setVisible(true);
        ListPane.setVisible(false);
        CalPane.setVisible(false);
    }

    public void ToListPane() {
        ListPane.setVisible(true);
        EvePane.setVisible(false);
        CalPane.setVisible(false);
    }

    //Menu Buttons
    public void dashboard(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DashboardAdmin.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Show an error message to the user
        }
    }

    public void subject(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SubjectAdmin.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            // Show an error message to the user
        }
    }

    public void course(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Course.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Show an error message to the user
        }
    }

    public void student(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Student.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Show an error message to the user
        }
    }

    public void faculty(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Faculty.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Show an error message to the user
        }
    }

    public void event(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Event.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Show an error message to the user
        }
    }
    // End Of Menu Controls #######################################################################

    //Event List Page Buttons
    public void EventAddPane() throws IOException {
        AddEvePane.setVisible(true);
        EditEvePane.setVisible(false);
        DeleteEvePane.setVisible(false);
    }

    public void EventDeletePane() throws IOException {
        AddEvePane.setVisible(false);
        EditEvePane.setVisible(false);
        DeleteEvePane.setVisible(true);
    }

    public void EventEditPane() throws IOException {
        AddEvePane.setVisible(false);
        EditEvePane.setVisible(true);
        DeleteEvePane.setVisible(false);
    }

    public void EventAddButton() throws IOException {
        EventTable.getItems().add(new Event(EventCodeTxt.getText(), EventNameTxt.getText(), EveDescTxt.getText(), EveLocaTxt.getText(), EveDTTxt.getText(), EveCapTxt.getText(), EveCostTxt.getText()));
        new EditEventList().Add(EventCodeTxt.getText(), EventNameTxt.getText(), EveDescTxt.getText(), EveLocaTxt.getText(), EveDTTxt.getText(), EveCapTxt.getText(), EveCostTxt.getText());
    }

    public void EventDeleteButton() throws IOException {
        if (!EventTable.getSelectionModel().getSelectedItem().toString().isBlank()) {
            System.err.println("DELETE " + EventTable.getSelectionModel().getSelectedItem().getEventName());
            new EditEventList().Remove(EventTable.getSelectionModel().getSelectedItem());
            currentEventList.remove(EventTable.getSelectionModel().getSelectedItem());
            EventTableViewList.setAll(currentEventList);
            EventTable.setItems(EventTableViewList);
        }
    }

    public void EventEditButton(ActionEvent event) throws IOException {
        if (!EventTable.getSelectionModel().getSelectedItems().isEmpty()) {
            System.err.println("EDIT " + EventTable.getSelectionModel().getSelectedItem().getEventName());
            new EditEventList().Edit(EventTable.getSelectionModel().getSelectedItem(), EventCodeEditTxt.getText(), EventNameEditTxt.getText(), EveDescEditTxt.getText(), EveLocaEditTxt.getText(), EveDTEditTxt.getText(), EveCapTxt.getText(), EveCostTxt.getText());
            for (Event Event : currentEventList) {
                if(Objects.equals(Event.getEventCode(), EventTable.getSelectionModel().getSelectedItem().getEventCode())
                        ||Objects.equals(Event.getEventName(), EventTable.getSelectionModel().getSelectedItem().getEventName())
                ) {currentEventList.remove(Event); break;}
            }
            currentEventList.remove(new Event(EventTable.getSelectionModel().getSelectedItem().getEventCode(), EventTable.getSelectionModel().getSelectedItem().getEventName(), EventTable.getSelectionModel().getSelectedItem().getEventDescription(), EventTable.getSelectionModel().getSelectedItem().getEventLocation(), EventTable.getSelectionModel().getSelectedItem().getEventDateNTime(), EventTable.getSelectionModel().getSelectedItem().getEventCapacity(), EventTable.getSelectionModel().getSelectedItem().getEventCost()));
            Event tempEvent = getEvent();
            currentEventList.add(tempEvent);
            EventTableViewList.set(EventTable.getSelectionModel().getSelectedIndex(), tempEvent);
            EventTable.setItems(EventTableViewList);
        }
    }

    private Event getEvent() {
        Event tempEvent;
        tempEvent = new Event(EventCodeEditTxt.getText(),EventNameEditTxt.getText(), EveDescEditTxt.getText(), EveLocaEditTxt.getText(), EveDTEditTxt.getText(), EveCapEditTxt.getText(), EveCostEditTxt.getText());
        return tempEvent;
    }
}