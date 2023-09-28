let toRepeat = document.getElementsByClassName("todo")[0];
let completed = document.getElementById("completed");
let remaining = document.getElementById("remaining");
completed.removeChild(toRepeat);

function populateData(response) {
  response.forEach((value) => {
      let clonedNode = toRepeat.cloneNode(true);
      clonedNode.getElementsByClassName("todo-id")[0].innerText = value.id;
      clonedNode.getElementsByClassName("todo-description")[0].innerText = value.description;
      clonedNode.getElementsByClassName("mark")[0].attributes["href"].value = `/todo/mark?id=${value.id}&completed=${!value.completed}`;
      if(value.completed) {
          completed.appendChild(clonedNode);
      } else {
          remaining.appendChild(clonedNode);
      }
  });
}

fetch("http://localhost:9001/todo/", {
  method: "GET",
  headers: {
    Accept: "application/json",
  },
})
  .then((response) => response.json())
  .then((response) => populateData(response));


function mark(event) {
    console.log(event);
}