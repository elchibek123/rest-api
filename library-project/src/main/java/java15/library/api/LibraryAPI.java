package java15.library.api;

import java15.library.dto.request.LibraryRequest;
import java15.library.dto.response.LibraryResponse;
import java15.library.dto.response.SimpleResponse;
import java15.library.service.LibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libraries")
@RequiredArgsConstructor
public class LibraryAPI {
    private final LibraryService libraryService;

    @GetMapping
    public List<LibraryResponse> findAll() {
        return libraryService.findAll();
    }

    @PostMapping
    public SimpleResponse save(@RequestBody LibraryRequest libraryRequest) {
        return libraryService.save(libraryRequest);
    }

    @PutMapping("{id}")
    public LibraryResponse updateLibrary(@PathVariable Long id, @RequestBody LibraryRequest libraryRequest) {
        return libraryService.updateLibrary(id, libraryRequest);
    }
}
