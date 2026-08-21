document.querySelectorAll(".menu a").forEach(link => {
    if (link.href.includes(window.location.pathname.split("/").pop())) {
        link.classList.add("active");
    }
});