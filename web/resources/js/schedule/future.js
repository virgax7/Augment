var dropZones = document.getElementsByClassName("dropZoneCell");
for (var i = 0; i < dropZones.length; i++) {
    dropZones[i].addEventListener("drop", dragDrop);
    dropZones[i].addEventListener("dragover", function () {
        event.preventDefault();
    });
}

var editButtons = document.getElementsByClassName("editButton");
for (var i = 0; i < editButtons.length; i++) {
    editButtons[i].addEventListener("click", showEditCell);
}

function setupDrag(goalWrapper) {
    goalWrapper.setAttribute("draggable", true);
    goalWrapper.addEventListener("dragstart", dragStart);
}

var currentEditCell;
var currentStatus;
function showEditCell(event) {
    if (currentEditCell != null && currentEditCell === event.target.parentNode.lastChild.innerHTML) {
        return;
    }
    if (currentEditCell != null && currentEditCell !== event.target.parentNode.lastChild.innerHTML) {
        closeEditCell();
    }
    currentEditCell = event.target.parentNode.lastChild.innerHTML;
    currentStatus = event.target.parentNode.parentNode.id;
    var xmlHttp = new XMLHttpRequest();
    var goalMap = new Map();
    xmlHttp.onload = function() {
        var goal = JSON.parse(this.response);
        for (var key in goal) {
            goalMap.set(key, goal[key]);
        }
    };
    xmlHttp.open("GET", "/rest/future/" + currentEditCell, false);
    xmlHttp.send();

    var editCell =
        '<div id="editCell">' +
            '<button id="closeEditCellButton"></button>' +
            '<form onsubmit="saveEdit()" id="editGoalForm">' +
                'Title: <input type="text" id="editTitle" value="' + goalMap.get("title") + '" required><br><br>' +
                'Description: <textarea id="editDescription" rows="15" cols="40">' + goalMap.get("description") + '</textarea><br><br>' +
                'Start Date: <input type="date" id="editStartDate" required><br><br>' +
                'Target Date: <input type="date" id="editTargetDate" required><br><br>' +
                'Status: <br><input type="radio" name="status" id="editStatus1" value=true>Not In Progress' +
                        '<input type="radio" name="status" id="editStatus2" value=true>In Progress' +
                        '<input type="radio" name="status" id="editStatus3" value=false>Accomplished<br><br>' +
                'Archived: <br><input type="radio" name="yesOrNo" id="editArchived" value=true>Yes' +
                           '<input type="radio" name="yesOrNo" value=false checked="checked">No<br><br>' +
                '<button id="saveButton" onclick="saveEdit()">Save</button>' +
            '</form>' +
        '</div>';
    document.getElementById("mainTable").insertAdjacentHTML('beforeend', editCell);
    document.getElementById("closeEditCellButton").addEventListener("click", closeEditCell);
    document.getElementById("editStartDate").valueAsDate = new Date(goalMap.get("startDate"));
    document.getElementById("editTargetDate").valueAsDate = new Date(goalMap.get("targetDate"));
}

function saveEdit(){
    var title = document.getElementById("editTitle").value;
    var description = document.getElementById("editDescription").value;
    var startDate = document.getElementById("editStartDate").value.replace(/(\d{4})-(\d{2})-(\d{2})/g, `$2/$3/$1`);
    var targetDate = document.getElementById("editTargetDate").value.replace(/(\d{4})-(\d{2})-(\d{2})/g, `$2/$3/$1`);
    var status = document.getElementById("editStatus1").checked ? "not_in_progress"
        : document.getElementById("editStatus2").checked ? "in_progress" : "accomplished";
    var archived = document.getElementById("editArchived").checked ? true : false;
    var xmlHttp = new XMLHttpRequest();
    var realStatus = status;
    if (!document.getElementById("editStatus1").checked && !document.getElementById("editStatus2").checked
        && !document.getElementById("editStatus3").checked) {
        realStatus = currentStatus === "dropZoneCell1" ? "not_in_progress" : currentStatus === "dropZoneCell2" ? "in_progress" : "accomplished";
    }
    var putUri = "/rest/future/editGoal/" + currentEditCell + "?newTitle=" + title + "&description=" + description +
    "&startDate=" + startDate + "&targetDate=" + targetDate + "&status=" + realStatus + "&archived=" + archived;
    xmlHttp.open("PUT", putUri);
    xmlHttp.send();
}

function closeEditCell() {
    var editCell = document.getElementById("editCell");
    editCell.parentNode.removeChild(editCell);
    currentEditCell = null;
}

function dragStart(event) {
    event.dataTransfer.dropEffect = "move";
    event.dataTransfer.setData("id", event.target.getAttribute("id"));
}

function dragDrop(event) {
    event.preventDefault();
    if (!isValidDropZone(event.target)) {
        return;
    }
    let eventTarget = event.target;
    if (eventTarget.getAttribute("class") !== "dropZoneCell") {
        while ((eventTarget = eventTarget.parentElement) && eventTarget.getAttribute("class") !== "dropZoneCell");
    }
    eventTarget.appendChild(document.getElementById(event.dataTransfer.getData("id")));
    var dropZoneCell = eventTarget.getAttribute("id");
    var status = dropZoneCell === "dropZoneCell1" ? "not_in_progress" : dropZoneCell === "dropZoneCell2" ? "in_progress" : "accomplished";
    var title = document.getElementById(event.dataTransfer.getData("id")).lastChild.innerHTML;
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open("PUT", "/rest/future/" + title + "?status=" + status);
    xmlHttp.send();
}

function isValidDropZone(eventTarget) {
    if (eventTarget.getAttribute("class") === "dropZoneCell") {
        return true;
    }
    while ((eventTarget = eventTarget.parentElement) && eventTarget.getAttribute("class") !== "dropZoneCell");
    return eventTarget !== null;
}
