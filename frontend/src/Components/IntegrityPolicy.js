function IntegrityPolicy() {


    return (
        <div className="container mt-5 border border-secondary rounded p-4">
            <div id="list-example" className="list-group">
                <a className="list-group-item list-group-item-action" href="#list-item-1">Item 1</a>
                <a className="list-group-item list-group-item-action" href="#list-item-2">Item 2</a>
                <a className="list-group-item list-group-item-action" href="#list-item-3">Item 3</a>
                <a className="list-group-item list-group-item-action" href="#list-item-4">Item 4</a>
            </div>
            <div data-bs-spy="scroll" data-bs-target="#list-example" data-bs-offset="0" class="scrollspy-example"
                 tabindex="0">
                <h4 id="list-item-1">Item 1</h4>
                <p>...</p>
                <h4 id="list-item-2">Item 2</h4>
                <p>...</p>
                <h4 id="list-item-3">Item 3</h4>
                <p>...</p>
                <h4 id="list-item-4">Item 4</h4>
                <p>...</p>
            </div>
        </div>
    )


}

export default IntegrityPolicy