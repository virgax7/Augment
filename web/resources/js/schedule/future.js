var cells = document.getElementsByClassName("cell");
for (var i = 0; i < cells.length; i++) {
    cells[i].setAttribute("draggable", true);
    cells[i].addEventListener("dragstart", dragStart);
}

var dropZones = document.getElementsByClassName("dropZone");
for (var i = 0; i < dropZones.length; i++) {
    dropZones[i].addEventListener("drop", dragDrop);
    dropZones[i].addEventListener("dragover", function() {
        event.preventDefault();
    });
}

function dragStart(event) {
    event.dataTransfer.dropEffect = "move";
    event.dataTransfer.setData("id", event.target.getAttribute("id"));
}

function dragDrop(event) {
    event.preventDefault();
    event.target.appendChild(document.getElementById(event.dataTransfer.getData("id")));
}

