var dropZones = document.getElementsByClassName("dropZoneCell");
for (var i = 0; i < dropZones.length; i++) {
    dropZones[i].addEventListener("drop", dragDrop);
    dropZones[i].addEventListener("dragover", function () {
        event.preventDefault();
    });
}

var editButtons = document.getElementsByClassName("editButton");
for (var i = 0; i < dropZones.length; i++) {
    editButtons[i].addEventListener("click", showEditCell);
}

function setupDrag(goalWrapper) {
    goalWrapper.setAttribute("draggable", true);
    goalWrapper.addEventListener("dragstart", dragStart);
}

function showEditCell() {
    var editCell =
        '<div id="editCell">' +
            '<button id="closeEditCellButton"></button>' +
        '</div>';
    document.getElementById("mainTable").insertAdjacentHTML('beforeend', editCell);
    document.getElementById("closeEditCellButton").addEventListener("click", closeEditCell);
}

function closeEditCell() {
    var editCell = document.getElementById("editCell");
    editCell.parentNode.removeChild(editCell);
}

function dragStart(event) {
    event.dataTransfer.dropEffect = "move";
    event.dataTransfer.setData("id", event.target.getAttribute("id"));
}

function dragDrop(event) {
    event.preventDefault();
    event.target.appendChild(document.getElementById(event.dataTransfer.getData("id")));
    var dropZoneCell = event.target.getAttribute("id");
    var status = dropZoneCell === "dropZoneCell1" ? "not_in_progress" : dropZoneCell === "dropZoneCell2" ? "in_progress" : "accomplished";
    var title = document.getElementById(event.dataTransfer.getData("id")).lastChild.innerHTML;
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open("PUT", "/rest/future/" + title + "?status=" + status);
    xmlHttp.send();
}

